import com.db4o.*;

public class Conexion {
    final static String BDEmp = "EMPLEADOS.YAP";
    static ObjectContainer db;
    static{
        db = Db4o.openFile(Db4o.newConfiguration(), BDEmp);
    }
    public static ObjectContainer getDBConexion(){
        return db;
    }
}