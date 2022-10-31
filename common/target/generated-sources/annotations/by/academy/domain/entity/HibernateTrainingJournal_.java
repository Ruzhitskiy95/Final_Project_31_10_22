package by.academy.domain.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HibernateTrainingJournal.class)
public abstract class HibernateTrainingJournal_ {

	public static volatile SingularAttribute<HibernateTrainingJournal, Long> reps;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> exerciseId;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> sets;
	public static volatile SingularAttribute<HibernateTrainingJournal, Boolean> isDeleted;
	public static volatile SingularAttribute<HibernateTrainingJournal, Timestamp> trainingData;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> totalWeight;
	public static volatile SingularAttribute<HibernateTrainingJournal, HibernateUser> trainingJournalExercise;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> weight;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> id;
	public static volatile SingularAttribute<HibernateTrainingJournal, Long> userId;
	public static volatile SingularAttribute<HibernateTrainingJournal, HibernateUser> userTraining;

	public static final String REPS = "reps";
	public static final String EXERCISE_ID = "exerciseId";
	public static final String SETS = "sets";
	public static final String IS_DELETED = "isDeleted";
	public static final String TRAINING_DATA = "trainingData";
	public static final String TOTAL_WEIGHT = "totalWeight";
	public static final String TRAINING_JOURNAL_EXERCISE = "trainingJournalExercise";
	public static final String WEIGHT = "weight";
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String USER_TRAINING = "userTraining";

}

