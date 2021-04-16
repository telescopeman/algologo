
public class Vector3D {
    public double x, y, z;

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D()
    {
        x = 0;
        y = 0;
        z = 0;
    }


    public Vector3D multiply(double factor)
    {
        return new Vector3D(x * factor, y * factor, z * factor);

    }
    public Vector3D multiply(Vector3D vector)
    {
        return new Vector3D(x * vector.x, y * vector.y, z * vector.z);
    }
    public Vector3D add(Vector3D vector)
    {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }
}
