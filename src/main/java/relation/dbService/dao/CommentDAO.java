package relation.dbService.dao;

import relation.dbService.dataSets.CommentDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

//PASSED
public class CommentDAO {
    private Session session;

    public CommentDAO(Session session) {
        this.session = session;
    }

    public CommentDataSet getComment(Long comment_id) throws HibernateException {
        Criteria criteria = session.createCriteria(CommentDataSet.class);
        return ((CommentDataSet) criteria.add(Restrictions.eq("comment_id", comment_id)).uniqueResult());
    }

    public Long insertComment(Long id, String text) throws HibernateException {
        return (Long) session.save(new CommentDataSet(id, text));
    }
}