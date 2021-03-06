package main.java.relation.dbService.dao;

import main.java.relation.dbService.dataSets.ArticleDataSet;
import main.java.relation.dbService.dataSets.CommentDataSet;
import main.java.relation.dbService.dataSets.MessageDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.UUID;

//PASSED
public class MessageDAO {
    private Session session;

    public MessageDAO(Session session) {
        this.session = session;
    }

    public MessageDataSet getMessage(Long id) throws HibernateException {
        Criteria criteria = session.createCriteria(MessageDataSet.class);
        return ((MessageDataSet) criteria.add(Restrictions.eq("messageId", id)).uniqueResult());
    }

    public Long insertMessage(Long userId, Boolean receaverMsgDeletedFlag, Boolean posterMsgDeletedFlag, String text, Date date) throws HibernateException {
        return (Long) session.save(new MessageDataSet(userId, receaverMsgDeletedFlag, posterMsgDeletedFlag, text, date));
    }
}