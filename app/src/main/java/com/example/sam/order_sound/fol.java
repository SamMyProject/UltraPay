package com.example.sam.order_sound;

/**
 * Created by Sam on 2018/2/2.
 */

public class fol {
    private static final double DEFAULT_ATTACK_TIME =  0.01;//in seconds
    /**
     * Defines how fast the envelope goes down, defined in seconds.
     */
    private static final double DEFAULT_RELEASE_TIME =  0.01;//in seconds
    double gainAttack ;
    double gainRelease;
    double envelopeOut = 0.0f;
    public fol(double sampleRate){
        gainAttack = (float) Math.exp(-1.0/(sampleRate*DEFAULT_ATTACK_TIME));
        gainRelease = (float) Math.exp(-1.0/(sampleRate*DEFAULT_RELEASE_TIME));
    }

    public double[] calculateEnvelope(double[] buffer){
        double[] b2=new double[buffer.length];
        for(int i = 0 ; i < buffer.length ; i++){
            double envelopeIn = Math.abs(buffer[i]);
            if(envelopeOut < envelopeIn){
                envelopeOut = envelopeIn + gainAttack * (envelopeOut - envelopeIn);
            } else {
                envelopeOut = envelopeIn + gainRelease * (envelopeOut - envelopeIn);
            }
            b2[i]=Math.pow(envelopeOut, 2);
        }
        return b2;
    }
}
