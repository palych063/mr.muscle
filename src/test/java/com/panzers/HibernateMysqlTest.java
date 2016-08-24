package com.panzers;

import com.panzers.entity.Kryptonite;
import org.hibernate.Session;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
public class HibernateMysqlTest extends Assert {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create( WebArchive.class )
                .addClass( Kryptonite.class )
                .addAsWebInfResource( EmptyAsset.INSTANCE, "beans.xml" )
                .addAsResource( "META-INF/persistence.xml" );
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional(value=TransactionMode.DEFAULT)
    public void testCreate() throws Exception {

        Session session = entityManager.unwrap( Session.class );

        Kryptonite kryptonite = new Kryptonite();
       // kryptonite.id = Long.parseLong(null);
        kryptonite.name = "Some Kryptonite";
        session.persist( kryptonite );

        session.flush();
        session.clear();

        Kryptonite loaded = session.find( Kryptonite.class, 1L );


        assertNotNull(kryptonite.getId());


    }
}
