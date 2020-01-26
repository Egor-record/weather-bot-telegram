package com.telegram;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Entity
public class Users {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private int id;

    @Column
    private long ChatID;

    @Column
    private Boolean IsSubscribed;

    @Column
    private float Lat;

    @Column
    private float Lon;

    public Users() {}

    public Users(SubscriberBuilder builder) {
        this.ChatID = builder.chatID;
        this.IsSubscribed = builder.IsSubscribed;
        this.Lat = builder.Lat;
        this.Lon = builder.Lon;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", ChatID=" + ChatID +
                ", IsSubscribed=" + IsSubscribed +
                ", Lat=" + Lat +
                ", Lon=" + Lon +
                '}';
    }

    /**
     * This method saves to the db new user with by default IsSubscribed false value
     * @param users
     */
    public void saveUser(Users users) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(users);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * This method returns all the list of subscribers no matter they subscribed or not
     * @return the list of subscribers
     */
    public static List<Users> fetchAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
        criteria.from(Users.class);
        List<Users> subscribers = session.createQuery(criteria).getResultList();
        session.close();
        return subscribers;
    }

    public static List<Users> findUserByChatID(long id) {

      Session session = sessionFactory.openSession();

      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
      Root<Users> root = criteria.from( Users.class );
      criteria.select( root );
      criteria.where( builder.equal( root.get( "ChatID" ), id) );
      List<Users> subscriber = session.createQuery(criteria).getResultList();
      session.close();
      return subscriber;
    };

    public static void update(Users users){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(users);
        session.getTransaction().commit();
        session.close();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatID() {
        return ChatID;
    }

    public void setChatID(int chatID) {
        ChatID = chatID;
    }

    public Boolean getSubscribed() {
        return IsSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        IsSubscribed = subscribed;
    }

    public float getLat() {
        return Lat;
    }

    public void setLat(float lat) {
        Lat = lat;
    }

    public float getLon() {
        return Lon;
    }

    public void setLon(float lon) {
        Lon = lon;
    }

    public static class SubscriberBuilder {

        private long chatID;
        private Boolean IsSubscribed;
        private float Lat;
        private float Lon;
        
        public SubscriberBuilder() {}


        public SubscriberBuilder withChatID(long chatID){
            this.chatID = chatID;
            return this;
        }

        public SubscriberBuilder withSubscription(boolean IsSubscribed) {
            this.IsSubscribed = IsSubscribed;
            return this;
        }

        public SubscriberBuilder withLongitude(float lon){
            this.Lon = lon;
            return this;
        }

        public SubscriberBuilder withLatitude(float lat){
            this.Lat = lat;
            return this;
        }

        public Users build() {
            return new Users(this);
        }

    }


}
