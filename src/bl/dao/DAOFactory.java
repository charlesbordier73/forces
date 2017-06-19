package bl.dao;

import persistence.FactoryDAOPG;

/**
 * Abstract factory to make DAO
 */
public abstract class DAOFactory {

    /**
     * Singleton instance
     */
    private static DAOFactory instance;

    /**
     * Empty constructor for singleton
     */
    protected DAOFactory() {
    }

    /**
     * Get the unique instance of DAO Factory
     *
     * @return An instance of DAOFactory
     */
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new FactoryDAOPG();
        }
        return instance;
    }

    public abstract ArticleDAO createArticleDAO();
    public abstract DistrictDAO createDistrictDAO();
    public abstract InfractionDAO createInfractionDAO();
    public abstract RequisitionDAO createRequisitionDAO();
    public abstract UnitDAO createUnitDAO();
    public abstract FeedbackDAO createFeedbackDAO();

}