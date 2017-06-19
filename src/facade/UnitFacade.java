package facade;

import bl.dao.DAOFactory;
import bl.dao.UnitDAO;
import bl.model.Unit;
import persistence.FactoryDAOPG;

import java.util.ArrayList;

public class UnitFacade {

    private static UnitFacade unitFacade = null;

    private Unit unit = null;

    public static UnitFacade getInstance() {
        if (unitFacade == null) {
            synchronized(UnitFacade.class) {
                if (unitFacade == null) {
                    unitFacade = new UnitFacade();
                }
            }
        }
        return unitFacade;
    }

    public void setCurrentUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getCurrentUnit() {
        return this.unit;
    }


    public Unit create(Unit unit) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        Unit createdUnit = unitDAO.create(unit);

        return createdUnit;
    }

    public boolean delete(Unit unit) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        boolean deleted = unitDAO.delete(unit);

        return deleted;
    }

    public Unit update(Unit unit) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        Unit updateUnit = unitDAO.update(unit);

        return updateUnit;
    }

    public ArrayList<Unit> search(String name) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        ArrayList<Unit> units = unitDAO.search(name);

        return units;
    }

    public Unit getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        Unit unit = unitDAO.getOne(id);

        return unit;
    }

    public ArrayList<Unit> getAllUnit() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        UnitDAO unitDAO = ((FactoryDAOPG) factory).createUnitDAO();
        ArrayList<Unit> units = unitDAO.getAllUnit();

        return units;
    }
}
