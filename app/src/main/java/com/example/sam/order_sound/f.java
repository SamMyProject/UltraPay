package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/2/2.
 */

public class f {
    private float a;
    private float b;
    private int c;
    private double[] d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;

    public f(float paramFloat1, float paramFloat2, double[] paramArrayOfDouble)
    {
        this.a = paramFloat1;
        this.b = paramFloat2;
        this.c = paramArrayOfDouble.length;
        this.d = paramArrayOfDouble;
        this.h = 0.14904226617617444D;
        this.i = -0.9888308262251285D;
        this.e = (2.0D * this.i);
    }

    private void a(double paramDouble)
    {
        double d1 = this.e;
        double d2 = this.f;
        double d3 = this.g;
        this.g = this.f;
        this.f = (d1 * d2 - d3 + paramDouble);
    }

    private void d()
    {
        this.g = 0.0D;
        this.f = 0.0D;
    }

    public void a()
    {
        float f1 = this.c;
        double d1 = 6.283185307179586D * (int)(0.5D + this.b * f1 / this.a) / f1;
        this.h = Math.sin(d1);
        this.i = Math.cos(d1);
        this.e = (2.0D * this.i);
        d();
    }

    public double b()
    {
        return this.f * this.f + this.g * this.g - this.f * this.g * this.e;
    }

    public double c()
    {
        int j = 0;
        while (j < this.c)
        {
            a(this.d[j]);
            j += 1;
        }
        double d1 = Math.sqrt(b());
        d();
        return d1;
    }
}
