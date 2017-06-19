package persistence;

import bl.dao.*;

/**
 * Factory to make PostgreSQL DAO for models
 */
public class FactoryDAOPG extends DAOFactory {

    @Override
    public ArticleDAO createArticleDAO() {
        return new ArticleDAOPG();
    }

    @Override
    public DistrictDAO createDistrictDAO() {
        return new DistrictDAOPG();
    }

    @Override
    public InfractionDAO createInfractionDAO() {
        return new InfractionDAOPG();
    }

    @Override
    public RequisitionDAO createRequisitionDAO() {
        return new RequisitionDAOPG();
    }

    @Override
    public UnitDAO createUnitDAO() {
        return new UnitDAOPG();
    }

    @Override
    public FeedbackDAO createFeedbackDAO() {
        return new FeedbackDAOPG();
    }
}