package ua.com.smiddle.scm;

import ua.com.smiddle.scm.model.ResultCode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

/**
 * @author ksa on 07.07.17.
 * @project scm_migrator
 */
public class Application {
    public static void main(String[] args) {
        List<String> toSkip = Arrays.asList(args);
        System.out.println("Initialized");
        if (toSkip != null && !toSkip.isEmpty())
            System.out.println("To skip:" + toSkip.stream().reduce(" , ", String::concat));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Smiddle");
        EntityManager em = emf.createEntityManager();
        System.out.println("Starting...");
        Query query;
        List<ResultCode> resultCodes = null;
        try {
            if (toSkip == null || toSkip.isEmpty())
                query = em.createQuery("SELECT rc FROM ResultCode rc", ResultCode.class);
            else
                query = em.createQuery("SELECT rc FROM ResultCode rc where rc.id not in :ids", ResultCode.class)
                        .setParameter("ids", toSkip);
            em.getTransaction().begin();
            resultCodes = (List<ResultCode>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return;
        }
        System.out.println("Founded result codes:");
        resultCodes.stream().forEach(e -> System.out.println(e.getId() + ", code=" + e.getCode()));

        long time;
        long totaltime = 0L;
        int result;
        int totalResult = 0;
        for (ResultCode rc : resultCodes) {
            time = System.currentTimeMillis();
            result = update(rc.getId(), em);
            System.out.println("Updated records=" + result + " execution time for id=" + rc.getId() + " is " + (System.currentTimeMillis() - time) + ", ms");
            totalResult = result != -1 ? totalResult + result : totalResult;
            totaltime += (System.currentTimeMillis() - time);
        }
        System.out.println("Total records:" + totalResult + ", time: " + totaltime + ", ms");
    }

    private static int update(long id, EntityManager em) {
        Query query;
        int i = -1;
        System.out.println("started for id=" + id);
        try {
            em.getTransaction().begin();
            query = em.createNativeQuery("UPDATE CM_RESULTS r1 join (select max(r2.id) as idd from CM_RESULTS r2 " +
                    "where r2.result_code_id=? group by r2.abonent_id,r2.result_code_id) q on q.idd=r1.ID set r1.LAST_RESULT=1")
                    .setParameter(1, id);
            i = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("exeption for id=" + id + " message: " + e.getMessage());
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        System.out.println("ended for id=" + id);
        return i;
    }
}
