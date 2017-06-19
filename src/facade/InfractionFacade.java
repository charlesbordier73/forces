package facade;


import bl.dao.DAOFactory;
import bl.dao.InfractionDAO;
import bl.model.Infraction;
import persistence.FactoryDAOPG;

import java.util.ArrayList;

public class InfractionFacade {


    private static InfractionFacade infractionFacade = null;

    private Infraction infraction = null;

    public static InfractionFacade getInstance() {
        if (infractionFacade == null) {
            synchronized(InfractionFacade.class) {
                if (infractionFacade == null) {
                    infractionFacade = new InfractionFacade();
                }
            }
        }
        return infractionFacade;
    }

    public void setCurrentInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    public Infraction getCurrentInfraction() {
        return this.infraction;
    }

    public Infraction create(Infraction infraction) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        Infraction createdInfraction = infractionDAO.create(infraction);

        return createdInfraction;
    }

    public boolean delete(Infraction infraction) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        boolean deleted = infractionDAO.delete(infraction);

        return deleted;
    }

    public Infraction update(Infraction infraction) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        Infraction updatedInfraction = infractionDAO.update(infraction);

        return  updatedInfraction;
    }

    public ArrayList<Infraction> search(String label) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        ArrayList<Infraction> infractions = infractionDAO.search(label);

        return infractions;
    }

    public Infraction getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        Infraction infraction = infractionDAO.getOne(id);

        return infraction;
    }

    public ArrayList<Infraction> getAllInfraction() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        InfractionDAO infractionDAO = ((FactoryDAOPG) factory).createInfractionDAO();
        ArrayList<Infraction> infractions = infractionDAO.getAllInfraction();

        return infractions;
    }
}
