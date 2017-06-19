package bl.dao;

import bl.model.Unit;

import java.util.ArrayList;


public abstract class UnitDAO {

    public abstract Unit create(Unit unit);

    public abstract boolean delete(Unit unit);

    public abstract Unit update(Unit unit);

    public abstract ArrayList<Unit> search(String name);

    public abstract Unit getOne(int id);

    public abstract ArrayList<Unit> getAllUnit();

}
