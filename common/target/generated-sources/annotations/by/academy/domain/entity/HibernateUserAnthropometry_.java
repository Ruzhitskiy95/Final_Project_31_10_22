package by.academy.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateUserAnthropometry.class)
public abstract class HibernateUserAnthropometry_ {

	public static volatile SingularAttribute<HibernateUserAnthropometry, Timestamp> date;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> quadriceps_size;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> calf_size;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> user_id;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> biceps_size;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> chest_size;
	public static volatile SingularAttribute<HibernateUserAnthropometry, HibernateUser> userAnthropometry;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> weight;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> id;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> waist_size;
	public static volatile SingularAttribute<HibernateUserAnthropometry, Long> height;

	public static final String DATE = "date";
	public static final String QUADRICEPS_SIZE = "quadriceps_size";
	public static final String CALF_SIZE = "calf_size";
	public static final String USER_ID = "user_id";
	public static final String BICEPS_SIZE = "biceps_size";
	public static final String CHEST_SIZE = "chest_size";
	public static final String USER_ANTHROPOMETRY = "userAnthropometry";
	public static final String WEIGHT = "weight";
	public static final String ID = "id";
	public static final String WAIST_SIZE = "waist_size";
	public static final String HEIGHT = "height";

}

