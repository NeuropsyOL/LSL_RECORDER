package com.example.aliayubkhan.LSLReceiver;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static com.example.aliayubkhan.LSLReceiver.MainActivity.LSLStreamName;
import static com.example.aliayubkhan.LSLReceiver.MainActivity.isAlreadyExecuted;
import static com.example.aliayubkhan.LSLReceiver.MainActivity.lv;
import static com.example.aliayubkhan.LSLReceiver.MainActivity.selectedItems;


/**
 * Created by aliayubkhan on 19/04/2018.
 */

public class LSLService extends Service {

    private static final String TAG = "LSLService";
    public static Thread t2;
    TextView tv;


    static LSL.StreamOutlet accelerometerOutlet;
    LSL.StreamInlet[] inlet;

    //Vector<Vector<Integer>> list = new Vector<Vector<Integer>>(100);
    //static Vector[] vectors = new Vector[10];

    //static ArrayList<Float>[] lightSample = new ArrayList<Float>[](4);

    //for int chanel format
    @SuppressWarnings("unchecked")
    ArrayList<Float>[] lightSample = new ArrayList[30];// = new ArrayList[];
    @SuppressWarnings("unchecked")
    ArrayList<Double>[] lightTimestamp = new ArrayList[30];


    //for int chanel format
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] lightSampleInt = new ArrayList[30];// = new ArrayList[];

    //for Double chanel format
    @SuppressWarnings("unchecked")
    ArrayList<Double>[] lightSampleDouble = new ArrayList[30];// = new ArrayList[];

    //for String chanel format
    @SuppressWarnings("unchecked")
    ArrayList<String>[] lightSampleString = new ArrayList[30];// = new ArrayList[];

    //for String chanel format
    @SuppressWarnings("unchecked")
    ArrayList<Short>[] lightSampleShort = new ArrayList[30];// = new ArrayList[];

    //for String chanel format
    @SuppressWarnings("unchecked")
    ArrayList<Byte>[] lightSampleByte = new ArrayList[30];// = new ArrayList[];

    //static Vector<Float> lightSample = new Vector<Float>(4);
    static Vector<Float> lightSample2 = new Vector<Float>(4);
    //Vector<Double> lightTimestamp = new Vector<Double>(4);
    Vector<Double> lightTimestamp2 = new Vector<Double>(4);
    float[][] sample = new float[1][1];
    int[][] sampleInt = new int[1][1];
    double[][] sampleDouble = new double[1][1];
    short[][] sampleShort = new short[1][1];
    byte[][] sampleByte = new byte[1][1];
    String[][] sampleString = new String[1][1];
    //float[][][] sample = new float[1][1][1];

    String[] streamHeader;
    String[] streamFooter;
    double[] offset;
    double[] lastValue;
    int streamCount;
    int[] chanelCount;
    String[] name;
    String[] format;

    double[][] timestamps;


    public LSLService(){
        super();
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this,"Service Created!", Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");
        Toast.makeText(this,"Recording LSL!", Toast.LENGTH_SHORT).show();

        //Recording.createInstance("test.xdf", streams);

        //File file = new File(MainActivity.filenamevalue);


//        Recording.writeDoubleManual();


        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR

        final LSL.StreamInfo[] results = LSL.resolve_streams();
        streamCount = results.length;

        inlet = new LSL.StreamInlet[results.length];
        streamHeader = new String[results.length];
        streamFooter = new String[results.length];
        offset = new double[results.length];
        lastValue = new double[results.length];
        chanelCount = new int[results.length];
        sample = new float[results.length][];
        sampleInt = new int[results.length][];
        sampleDouble = new double[results.length][];
        sampleString = new String[results.length][];
        sampleShort = new short[results.length][];
        sampleByte = new byte[results.length][];
        timestamps = new double[results.length][];
        name = new String[results.length];
        format = new String[results.length];

        for (int i=0; i<inlet.length; i++){
            final int finalI = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!MainActivity.checkFlag) {
                        try {

                            inlet[finalI] = new LSL.StreamInlet(results[finalI]);
                            LSL.StreamInfo inf = inlet[finalI].info();
                            chanelCount[finalI] = inlet[finalI].info().channel_count();
                            System.out.println("The stream's XML meta-data is: ");
                            System.out.println(inf.as_xml());
                            streamHeader[finalI] = inf.as_xml();
                            format[finalI] = getXmlNodeValue(inf.as_xml(), "channel_format");
//                                if (chanelCount[i] == 1){
//                                    offset[i] = inlet[i].time_correction();
//                                }

                            if (format[finalI].contains("float")){
                                sample[finalI] = new float[inlet[finalI].info().channel_count()];
                                lightSample[finalI] = new ArrayList<Float>(4);
                            } else if(format[finalI].contains("int")){
                                sampleInt[finalI] = new int[inlet[finalI].info().channel_count()];
                                lightSampleInt[finalI] = new ArrayList<Integer>(4);
                            } else if(format[finalI].contains("double")){
                                sampleDouble[finalI] = new double[inlet[finalI].info().channel_count()];
                                lightSampleDouble[finalI] = new ArrayList<Double>(4);
                            } else if(format[finalI].contains("string")){
                                sampleString[finalI] = new String[inlet[finalI].info().channel_count()];
                                lightSampleString[finalI] = new ArrayList<String>(4);
                            } else if(format[finalI].contains("byte")){
                                sampleByte[finalI] = new byte[inlet[finalI].info().channel_count()];
                                lightSampleByte[finalI] = new ArrayList<Byte>(4);
                            } else if(format[finalI].contains("short")){
                                sampleShort[finalI] = new short[inlet[finalI].info().channel_count()];
                                lightSampleShort[finalI] = new ArrayList<Short>(4);
                            }
                            timestamps[finalI] = new double[1];
                            lightTimestamp[finalI] = new ArrayList<Double>(4);
                            name[finalI] = inlet[finalI].info().name();

                            //receive Data
                            //sample = new float[inlet.info().channel_count()];
                            //timestamps = new double[1];

                            while (true) {

                                if (format[finalI].contains("float")){
                                    inlet[finalI].pull_chunk(sample[finalI], timestamps[finalI]);
                                } else if(format[finalI].contains("int")){
                                    inlet[finalI].pull_chunk(sampleInt[finalI], timestamps[finalI]);
                                } else if(format[finalI].contains("double")){
                                    inlet[finalI].pull_chunk(sampleDouble[finalI], timestamps[finalI]);
                                } else if(format[finalI].contains("string")){
                                    inlet[finalI].pull_chunk(sampleString[finalI], timestamps[finalI]);
                                } else if(format[finalI].contains("byte")){
                                    inlet[finalI].pull_chunk(sampleByte[finalI], timestamps[finalI]);
                                } else if(format[finalI].contains("short")){
                                    inlet[finalI].pull_chunk(sampleShort[finalI], timestamps[finalI]);
                                }

                                if (format[finalI].contains("float")){
                                    for(int k=0;k<sample[finalI].length;k++){
                                        lightSample[finalI].add(k,sample[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                } else if(format[finalI].contains("int")){
                                    for(int k=0;k<sampleInt[finalI].length;k++){
                                        lightSampleInt[finalI].add(k,sampleInt[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                } else if(format[finalI].contains("double")){
                                    for(int k=0;k<sampleDouble[finalI].length;k++){
                                        lightSampleDouble[finalI].add(k,sampleDouble[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                } else if(format[finalI].contains("string")){
                                    for(int k=0;k<sampleString[finalI].length;k++){
                                        lightSampleString[finalI].add(k,sampleString[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                } else if(format[finalI].contains("byte")){
                                    for(int k=0;k<sampleByte[finalI].length;k++){
                                        lightSampleByte[finalI].add(k,sampleByte[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                } else if(format[finalI].contains("short")){
                                    for(int k=0;k<sampleShort[finalI].length;k++){
                                        lightSampleShort[finalI].add(k,sampleShort[finalI][k]);
                                        if(k==0){
                                            lightTimestamp[finalI].add(k,timestamps[finalI][k]);
                                        }
                                    }
                                }

                                System.out.println();
                                System.out.println("Thread ID is: " + finalI);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    //Stop service once it finishes its taskstopSelf();
                }
            }).start();
        }

//        new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (!MainActivity.checkFlag) {
//
////                        LSL.StreamInfo[] results = LSL.resolve_streams();
////                        streamCount = results.length;
//                        try {
////                            inlet = new LSL.StreamInlet[results.length];
////                            streamHeader = new String[results.length];
////                            streamFooter = new String[results.length];
////                            offset = new double[results.length];
////                            lastValue = new double[results.length];
////                            chanelCount = new int[results.length];
////                            sample = new float[results.length][];
////                            sampleInt = new int[results.length][];
////                            sampleDouble = new double[results.length][];
////                            sampleString = new String[results.length][];
////                            sampleShort = new short[results.length][];
////                            sampleByte = new byte[results.length][];
////                            timestamps = new double[results.length][];
////                            name = new String[results.length];
////                            format = new String[results.length];
//                            //lightSample = new ArrayList[results.length];
//                            //lightTimestamp = new ArrayList[results.length];
//
//                            for (int i=0; i<inlet.length; i++){
//                                inlet[i] = new LSL.StreamInlet(results[i]);
//                                LSL.StreamInfo inf = inlet[i].info();
//                                chanelCount[i] = inlet[i].info().channel_count();
//                                System.out.println("The stream's XML meta-data is: ");
//                                System.out.println(inf.as_xml());
//                                streamHeader[i] = inf.as_xml();
//                                format[i] = getXmlNodeValue(inf.as_xml(), "channel_format");
////                                if (chanelCount[i] == 1){
////                                    offset[i] = inlet[i].time_correction();
////                                }
//
//                                if (format[i].contains("float")){
//                                    //sample = new float[results.length][];
//                                    sample[i] = new float[inlet[i].info().channel_count()];
//                                    lightSample[i] = new ArrayList<Float>(4);
//                                } else if(format[i].contains("int")){
//                                    //sampleInt = new int[results.length][];
//                                    sampleInt[i] = new int[inlet[i].info().channel_count()];
//                                    lightSampleInt[i] = new ArrayList<Integer>(4);
//                                } else if(format[i].contains("double")){
//                                    //sampleDouble = new double[results.length][];
//                                    sampleDouble[i] = new double[inlet[i].info().channel_count()];
//                                    lightSampleDouble[i] = new ArrayList<Double>(4);
//                                } else if(format[i].contains("string")){
//                                    //sampleString = new String[results.length][];
//                                    sampleString[i] = new String[inlet[i].info().channel_count()];
//                                    lightSampleString[i] = new ArrayList<String>(4);
//                                } else if(format[i].contains("byte")){
//                                    //sampleString = new String[results.length][];
//                                    sampleByte[i] = new byte[inlet[i].info().channel_count()];
//                                    lightSampleByte[i] = new ArrayList<Byte>(4);
//                                } else if(format[i].contains("short")){
//                                    //sampleString = new String[results.length][];
//                                    sampleShort[i] = new short[inlet[i].info().channel_count()];
//                                    lightSampleShort[i] = new ArrayList<Short>(4);
//                                }
//                                timestamps[i] = new double[1];
//                                lightTimestamp[i] = new ArrayList<Double>(4);
//                                name[i] = inlet[i].info().name();
//
//                            }
//
//                            //receive Data
//                            //sample = new float[inlet.info().channel_count()];
//                            //timestamps = new double[1];
//
//                            while (true) {
//
//                                for (int i=0; i<inlet.length; i++) {
//                                    if (format[i].contains("float")){
//                                        inlet[i].pull_chunk(sample[i], timestamps[i]);
//                                    } else if(format[i].contains("int")){
//                                        inlet[i].pull_chunk(sampleInt[i], timestamps[i]);
//                                    } else if(format[i].contains("double")){
//                                        inlet[i].pull_chunk(sampleDouble[i], timestamps[i]);
//                                    } else if(format[i].contains("string")){
//                                        inlet[i].pull_chunk(sampleString[i], timestamps[i]);
//                                    } else if(format[i].contains("byte")){
//                                        inlet[i].pull_chunk(sampleByte[i], timestamps[i]);
//                                    } else if(format[i].contains("short")){
//                                        inlet[i].pull_chunk(sampleShort[i], timestamps[i]);
//                                    }
//
//                                }
//
//
//
////                                for (int i=0; i<inlet.length; i++) {
////                                    for(int j=0; j<chanelCount[i]; j++){
////                                        inlet[i].pull_chunk(sample[i][j], timestamps[i]);
////                                    }
////                                }
//
//                                //inlet[0].pull_chunk(sample[0], timestamps[0]);
//
////                                for (int k=0;k<sample[0].length;k++) {
//////                                    System.out.print("\t" + Double.toString(sample[k]));
//////                                    System.out.print("\t" + Double.toString(timestamps[k]));
////                                    lightSample[0].add(k,sample[0][k]);
////                                    lightTimestamp[0].add(k,timestamps[0][k]);
////
////                                }
//
//
////                                for (int i=0; i<inlet.length; i++) {
////                                    for (int j=0; j<chanelCount[i]; j++) {
////                                        for(int k=0;k<sample[i].length;k++){
////                                            lightSample[i].add(k,sample[j][k]);
////                                            lightTimestamp[i].add(k,timestamps[i][k]);
////                                        }
////
////
////                                    }
////                                }
//
//
//                                for (int i=0; i<inlet.length; i++) {
//
//                                    if (format[i].contains("float")){
//                                        for(int k=0;k<sample[i].length;k++){
//                                            lightSample[i].add(k,sample[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    } else if(format[i].contains("int")){
//                                        for(int k=0;k<sampleInt[i].length;k++){
//                                            lightSampleInt[i].add(k,sampleInt[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    } else if(format[i].contains("double")){
//                                        for(int k=0;k<sampleDouble[i].length;k++){
//                                            lightSampleDouble[i].add(k,sampleDouble[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    } else if(format[i].contains("string")){
//                                        for(int k=0;k<sampleString[i].length;k++){
//                                            lightSampleString[i].add(k,sampleString[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    } else if(format[i].contains("byte")){
//                                        for(int k=0;k<sampleByte[i].length;k++){
//                                            lightSampleByte[i].add(k,sampleByte[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    } else if(format[i].contains("short")){
//                                        for(int k=0;k<sampleShort[i].length;k++){
//                                            lightSampleShort[i].add(k,sampleShort[i][k]);
//                                            if(k==0){
//                                                lightTimestamp[i].add(k,timestamps[i][k]);
//                                            }
//                                        }
//                                    }
//
//                                }
//
////                                for (int k=0;k<sample.length;k++) {
//////                                    System.out.print("\t" + Double.toString(sample[k]));
//////                                    System.out.print("\t" + Double.toString(timestamps[k]));
////                                    lightSample.add(sample[k]);
////                                    lightTimestamp.add(timestamps[k]);
////                                }
//
////                                for (int k=0;k<sample1.length;k++) {
//////                                    System.out.print("\t" + Double.toString(sample[k]));
//////                                    System.out.print("\t" + Double.toString(timestamps[k]));
////                                    lightSample2.add(sample1[k]);
////                                    lightTimestamp2.add(timestamps1[k]);
////                                }
//                                System.out.println();
//
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                    //Stop service once it finishes its taskstopSelf();
//                }
//        }).start();
        MainActivity.isRunning = true;
        return Service.START_STICKY;
    }


    public native String createXdfFile(String temp, float[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);
    public native String createXdfFileInt(String temp, int[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);
    public native String createXdfFileDouble(String temp, double[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);
    public native String createXdfFileString(String temp, String[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);
    public native String createXdfFileShort(String temp, short[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);
    public native String createXdfFileByte(String temp, byte[] lightSample, double[] lightTimestamps, String streamMetaData, String metadata, String offset, String lastValue, int i, int i1);


    static {
        System.loadLibrary("generate_xdf");
    }

    String getXmlNodeValue(String xmlString, String nodeName){
        int start = xmlString.indexOf("<"+nodeName+">") + nodeName.length() + 2;
        int end = xmlString.indexOf("</"+nodeName+">");
        return xmlString.substring(start, end);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        MainActivity.isRunning = false;

        if(LSLStreamName != null){
            LSLStreamName.clear();
            lv.setAdapter(new ArrayAdapter<String>(this,R.layout.list_view_text , LSLStreamName));
        }

//        /*Display Vector elements*/
//        Enumeration en = lightTimestamp.elements();
//        System.out.println("\nElements are:");
//        while(en.hasMoreElements())
//            System.out.print(en.nextElement() + " ");
//
//        System.out.println("testData");
//
//
        MainActivity.path = MainActivity.path + "/" + MainActivity.filenamevalue;

        System.out.println("bbreakpoint");

        for (int i=0; i<streamCount; i++){

            if(selectedItems.contains(name[i])){

                if (format[i].contains("float")){
                    float[] lightsample = ArrayUtils.toPrimitive(lightSample[i].toArray(new Float[0]), 0);
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    if(!name[i].contains("Audio")){
                        lightsample = removeZerosFloat(lightsample, lightsample.length);
                    }


                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    if (chanelCount[i] == 1){

                        if(name[i].contains("Audio")){
                            lightsample = appendZeros(lightsample, lighttimestamps);
                            lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                        } else {
                            lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                        }

                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFile(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);
                } else if(format[i].contains("int")){

                    int[] lightsample = ArrayUtils.toPrimitive(lightSampleInt[i].toArray(new Integer[0]), 0);
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    lightsample = removeZerosInt(lightsample, lightsample.length);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    if (chanelCount[i] == 1){
                        lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFileInt(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);

                } else if(format[i].contains("double")){

                    double[] lightsample = ArrayUtils.toPrimitive(lightSampleDouble[i].toArray(new Double[0]), 0);
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    lightsample = removeZerosDouble(lightsample, lightsample.length);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    if (chanelCount[i] == 1){
                        lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFileDouble(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);

                } else if(format[i].contains("string")){

                    String[] lightsample = lightSampleString[i].toArray(new String[0]);//ArrayUtils.toPrimitive(lightSampleString[i].toArray(new String[0]), 0);
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    //lightsample = removeZerosFloat(lightsample, lightsample.length);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);
                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    if (chanelCount[i] == 1){
                        lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFileString(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);

                } else if(format[i].contains("short")){

                    short[] lightsample = new short[1];

                    for (int k=0; k<lightSampleShort[i].size(); k++){
                        lightsample[i] = lightSampleShort[i].get(k).shortValue();
                    }
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    lightsample = removeZerosShort(lightsample, lightsample.length);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    if (chanelCount[i] == 1){
                        lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFileShort(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);

                } else if(format[i].contains("byte")){

                    byte[] lightsample = new byte[1];

                    for (int k=0; k<lightSampleByte[i].size(); k++){
                        lightsample[i] = lightSampleByte[i].get(k).byteValue();
                    }
                    //float[] testlightsample = Arrays.copyOfRange(lightsample, 100, 112);
                    double[] lighttimestamps = ArrayUtils.toPrimitive(lightTimestamp[i].toArray(new Double[0]), 0);
                    //double[] testlighttimestamps = Arrays.copyOfRange(lighttimestamps, 100, 104);

                    //                                const std::string footer(
//                                    "<?xml version=\"1.0\"?>"
//                                    "<info>"
//                                    "<first_timestamp>5.1</first_timestamp>"
//                                    "<last_timestamp>5.9</last_timestamp>"
//                                    "<sample_count>9</sample_count>"
//                                    "<clock_offsets>"
//                                    "<offset><time>50979.7660030605</time><value>-3.436503902776167e-06</value></offset>"
//                                    "</clock_offsets></info>");


                    lightsample = removeZerosByte(lightsample, lightsample.length);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);

                    lighttimestamps = invertTimestamps(lighttimestamps);
                    lighttimestamps = removeZerosDouble(lighttimestamps, lighttimestamps.length);


                    if (chanelCount[i] == 1){
                        lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
                    } else {
                        lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    }
                    //lightsample = Arrays.copyOfRange(lightsample, 0, lightsample.length-2000);
                    //lighttimestamps = Arrays.copyOfRange(lighttimestamps, 0, lightsample.length);
//            lightsample = Arrays.copyOfRange(lightsample, 0, lighttimestamps.length*chanelCount[i]);
                    System.out.println("lengt of timestamps is: "+ lighttimestamps.length);

                    streamFooter[i] = "<?xml version=\"1.0\"?>" + "\n"+
                            "<info>" + "\n\t" +
                            "<first_timestamp>" + lighttimestamps[0] +"</first_timestamp>" + "\n\t" +
                            "<last_timestamp>" + lighttimestamps[lighttimestamps.length - 1] + "</last_timestamp>" + "\n\t" +
                            "<sample_count>"+ lightsample.length +"</sample_count>" + "\n\t" +
                            "<clock_offsets>" +
                            "<offset><time>"+ lighttimestamps[lighttimestamps.length - 1] +"</time><value>"+ offset[i] + "</value></offset>" +
                            "</clock_offsets>" +"\n"+ "</info>";

                    lastValue[i] = lighttimestamps[lighttimestamps.length - 1];


                    String path = createXdfFileByte(MainActivity.path, lightsample, lighttimestamps, streamHeader[i], streamFooter[i], String.valueOf(offset[i]), String.valueOf(lastValue[i]), i, chanelCount[i]);

                    if(i == selectedItems.size()-1){
                        Toast.makeText(this, "File written at: " + path, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Writing file please wait!", Toast.LENGTH_LONG).show();
                    }
                    //Log.d(TAG, "onActivityResult: "+"Path is: checking" +path);

                }
            }

        }

        isAlreadyExecuted = true;

    }

    public float[] appendZeros(float[] sample, double[] timestamps){
        float[] data1 = new float[1];

        int dif = timestamps.length - sample.length;

        data1 = Arrays.copyOf(sample, sample.length + dif);

        for (int i=sample.length; i<timestamps.length; i++){
            data1[i] = 0;
        }

        return data1;
    }


//    public static void triggerRebirth(Context context, Intent nextIntent) {
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(KEY_RESTART_INTENT, nextIntent);
//        context.startActivity(intent);
//        if (context instanceof Activity) {
//            ((Activity) context).finish();
//        }
//
//        Runtime.getRuntime().exit(0);
//    }
    // removing leading zeros
    static int[] removeZerosInt(int[] a, int n)
    {

        // index to store the first
        // non-zero number
        int ind = -1;

        // traverse in the array and find the first
        // non-zero number
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                ind = i;
                break;
            }
        }

        // if no non-zero number is there
        if (ind == -1) {
            System.out.print("Array has leading zeros only");
            return a;
        }

        // Create an array to store
        // numbers apart from leading zeros
        int[] b = new int[n - ind];

        // store the numbers removing leading zeros
        for (int i = 0; i < n - ind; i++)
            b[i] = a[ind + i];

//        // print the array
//        for (int i = 0; i < n - ind; i++)
//            System.out.print(b[i]+" ");
        return b;
    }

    // removing leading zeros
    static float[] removeZerosFloat(float[] a, int n)
    {

        // index to store the first
        // non-zero number
        int ind = -1;

        // traverse in the array and find the first
        // non-zero number
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                ind = i;
                break;
            }
        }

        // if no non-zero number is there
        if (ind == -1) {
            System.out.print("Array has leading zeros only");
            return a;
        }

        // Create an array to store
        // numbers apart from leading zeros
        float[] b = new float[n - ind];

        // store the numbers removing leading zeros
        for (int i = 0; i < n - ind; i++)
            b[i] = a[ind + i];

//        // print the array
//        for (int i = 0; i < n - ind; i++)
//            System.out.print(b[i]+" ");

        return b;
    }

    // removing leading zeros
    static double[] removeZerosDouble(double[] a, int n)
    {

        // index to store the first
        // non-zero number
        int ind = -1;

        // traverse in the array and find the first
        // non-zero number
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                ind = i;
                break;
            }
        }

        // if no non-zero number is there
        if (ind == -1) {
            System.out.print("Array has leading zeros only");
            return a;
        }

        // Create an array to store
        // numbers apart from leading zeros
        double[] b = new double[n - ind];

        // store the numbers removing leading zeros
        for (int i = 0; i < n - ind; i++)
            b[i] = a[ind + i];

//        // print the array
//        for (int i = 0; i < n - ind; i++)
//            System.out.print(b[i]+" ");
        return b;
    }

    // removing leading zeros
    static short[] removeZerosShort(short[] a, int n)
    {

        // index to store the first
        // non-zero number
        int ind = -1;

        // traverse in the array and find the first
        // non-zero number
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                ind = i;
                break;
            }
        }

        // if no non-zero number is there
        if (ind == -1) {
            System.out.print("Array has leading zeros only");
            return a;
        }

        // Create an array to store
        // numbers apart from leading zeros
        short[] b = new short[n - ind];

        // store the numbers removing leading zeros
        for (int i = 0; i < n - ind; i++)
            b[i] = a[ind + i];

//        // print the array
//        for (int i = 0; i < n - ind; i++)
//            System.out.print(b[i]+" ");
        return b;
    }

    // removing leading zeros
    static byte[] removeZerosByte(byte[] a, int n)
    {

        // index to store the first
        // non-zero number
        int ind = -1;

        // traverse in the array and find the first
        // non-zero number
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                ind = i;
                break;
            }
        }

        // if no non-zero number is there
        if (ind == -1) {
            System.out.print("Array has leading zeros only");
            return a;
        }

        // Create an array to store
        // numbers apart from leading zeros
        byte[] b = new byte[n - ind];

        // store the numbers removing leading zeros
        for (int i = 0; i < n - ind; i++)
            b[i] = a[ind + i];

//        // print the array
//        for (int i = 0; i < n - ind; i++)
//            System.out.print(b[i]+" ");
        return b;
    }

    double[] invertTimestamps(double[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            double temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}