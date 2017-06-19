package facade;

import bl.dao.DAOFactory;
import bl.dao.DistrictDAO;
import bl.model.District;
import persistence.FactoryDAOPG;

import java.util.ArrayList;


public class DistrictFacade {

    private static DistrictFacade districtFacade = null;

    private District district = null;

    public static DistrictFacade getInstance() {
        if (districtFacade == null) {
            synchronized(DistrictFacade.class) {
                if (districtFacade == null) {
                    districtFacade = new DistrictFacade();
                }
            }
        }
        return districtFacade;
    }

    public void setCurrentDistrict(District district) {
        this.district = district;
    }

    public District getCurrentDistrict() {
        return this.district;
    }

    public District create(District district) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        District createdDistrict = districtDAO.create(district);

        return createdDistrict;
    }

    public boolean delete(District district) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        boolean deleted = districtDAO.delete(district);

        return deleted;
    }

    public District update(District district) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        District updatedDistrict = districtDAO.update(district);

        return updatedDistrict;
    }

    public ArrayList<District> search(String name) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        ArrayList<District> districts = districtDAO.search(name);

        return districts;
    }

    public District getOne(int id) {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        District district = districtDAO.getOne(id);

        return district;
    }

    public ArrayList<District> getAllDistrict() {
        DAOFactory factory = FactoryDAOPG.getInstance();
        DistrictDAO districtDAO = ((FactoryDAOPG) factory).createDistrictDAO();
        ArrayList<District> districts = districtDAO.getAllDistrict();

        return districts;
    }

}
