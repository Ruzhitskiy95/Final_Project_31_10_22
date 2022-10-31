package by.academy.domain.entity;

import by.academy.domain.Gender;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateUser.class)
public abstract class HibernateUser_ {

	public static volatile SingularAttribute<HibernateUser, Timestamp> modificationDate;
	public static volatile SingularAttribute<HibernateUser, String> surName;
	public static volatile SingularAttribute<HibernateUser, Boolean> isDeleted;
	public static volatile SingularAttribute<HibernateUser, Gender> gender;
	public static volatile SingularAttribute<HibernateUser, Credentials> credentials;
	public static volatile SetAttribute<HibernateUser, HibernateRole> roles;
	public static volatile SingularAttribute<HibernateUser, Long> id;
	public static volatile SingularAttribute<HibernateUser, String> userName;
	public static volatile SingularAttribute<HibernateUser, Timestamp> creationDate;
	public static volatile SingularAttribute<HibernateUser, Timestamp> birthDate;
	public static volatile SetAttribute<HibernateUser, HibernateTrainingJournal> trainingJournalsSets;
	public static volatile SetAttribute<HibernateUser, HibernateUserAnthropometry> userAnthropometrySet;

	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String SUR_NAME = "surName";
	public static final String IS_DELETED = "isDeleted";
	public static final String GENDER = "gender";
	public static final String CREDENTIALS = "credentials";
	public static final String ROLES = "roles";
	public static final String ID = "id";
	public static final String USER_NAME = "userName";
	public static final String CREATION_DATE = "creationDate";
	public static final String BIRTH_DATE = "birthDate";
	public static final String TRAINING_JOURNALS_SETS = "trainingJournalsSets";
	public static final String USER_ANTHROPOMETRY_SET = "userAnthropometrySet";

}

