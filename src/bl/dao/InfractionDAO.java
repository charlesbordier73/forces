package bl.dao;

import bl.model.Infraction;

import java.util.ArrayList;


public abstract class InfractionDAO {

    public abstract Infraction create(Infraction infraction);

    public abstract boolean delete(Infraction infraction);

    public abstract Infraction update(Infraction infraction);

    public abstract ArrayList<Infraction> search(String label);

    public abstract Infraction getOne(int id);

    public abstract ArrayList<Infraction> getAllInfraction();

}
