package App.Football.Factories;

import java.util.List;

public interface IBusiness<T> {
    public boolean Save(T obj);
    public boolean Update(T obj);
    public boolean Delete(int id);
    public List<T> All();
    public T Find(int id);
}
