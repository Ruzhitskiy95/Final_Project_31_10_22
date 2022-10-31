package by.academy.domain.entity;

import by.academy.domain.SystemRoles;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateRole.class)
public abstract class HibernateRole_ {

	public static volatile SingularAttribute<HibernateRole, Timestamp> modificationDate;
	public static volatile SingularAttribute<HibernateRole, SystemRoles> roleName;
	public static volatile SingularAttribute<HibernateRole, Long> id;
	public static volatile SingularAttribute<HibernateRole, Timestamp> creationDate;
	public static volatile SetAttribute<HibernateRole, HibernateUser> users;

	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String ROLE_NAME = "roleName";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USERS = "users";

}

