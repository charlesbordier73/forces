package bl.dao;

import bl.model.District;

import java.util.ArrayList;


public abstract class DistrictDAO {

    public abstract District create(District district);

    public abstract boolean delete(District district);

    public abstract District update(District district);

    public abstract ArrayList<District> search(String name);

    public abstract District getOne(int id);

    public abstract ArrayList<District> getAllDistrict();

}
