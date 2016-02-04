import com.db4o.*;

public class Conexion {
    final static String BDDep = "DEPARTAMENTOS.YAP";
    static ObjectContainer db;
    static{
        db = Db4o.openFile(Db4o.newConfiguration(), BDDep);
    }
    public static ObjectContainer getDBConexion(){
        return db;
    }
}