package by.academy.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateMedicalInfo.class)
public abstract class HibernateMedicalInfo_ {

	public static volatile SingularAttribute<HibernateMedicalInfo, Timestamp> modificationDate;
	public static volatile SingularAttribute<HibernateMedicalInfo, String> rh;
	public static volatile SingularAttribute<HibernateMedicalInfo, Long> id;
	public static volatile SingularAttribute<HibernateMedicalInfo, Long> bloodType;
	public static volatile SingularAttribute<HibernateMedicalInfo, Timestamp> creationDate;
	public static volatile SingularAttribute<HibernateMedicalInfo, Long> userId;

	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String RH = "rh";
	public static final String ID = "id";
	public static final String BLOOD_TYPE = "bloodType";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER_ID = "userId";

}

