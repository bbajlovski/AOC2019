package mk.aoc;

import mk.aoc.util.FileUtil;
import mk.aoc.util.Tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day05 {//

    public static void main(String... args) {
        FileUtil file = new FileUtil();

        long startTime = System.nanoTime();
        long duration1 = -1;
        long duration2 = -1;

        BufferedReader reader;
        String outputVal = "";
        String outputVal2 = "-1";
        try {
            reader = new BufferedReader(new FileReader(file.getFileFromResources("Day05.txt")));
            String line = reader.readLine();
            while (line != null) {
                System.out.println("-> " + line);

                String[] input = line.split(",");

                outputVal = allOutput(input, null, null, "1");
                long endTime1 =  System.nanoTime();

                String[] input2 = line.split(",");

                outputVal2 = allOutput(input2, null, null, "5");
                long endTime2 =  System.nanoTime();

                duration1 = (endTime1 - startTime) / 1_000_000;
                duration2 = (endTime2 - startTime) / 1_000_000;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("1:: " + outputVal + " / " + duration1 + "ms");
        System.out.println("2:: " + outputVal2 + " / " + duration2 + "ms");
    }



    public static String allOutput(String[] opInput, String noun, String verb, String inputVal) {
        if (Properties.print) {
            System.out.println("========================== noun=" + noun + ", verb=" + verb + ", inputVal=" + inputVal + " =========================================================");
        }

//        int[] opInput = Tools.convertStringArrayToIntArray(input);
        opInput[1] = noun != null ? noun : opInput[1];
        opInput[2] = verb != null ? noun : opInput[2];

        int pos = 0;
        String outputVal = "";
        boolean halt = pos >= opInput.length;
        while (!halt) {
            String fullCode = opInput[pos];

            while (fullCode.length() < 5) {
                fullCode = "0" + fullCode;
            }

            if (Properties.print) {
                System.out.println(":: [" + pos + "] :: FULLCODE :: " + fullCode);
            }

            int opCode = Integer.parseInt(fullCode.substring(3));
            int modeParam1 = Integer.parseInt(fullCode.substring(2,3));
            int modeParam2 = Integer.parseInt(fullCode.substring(1,2));

            int dest = pos+3 < opInput.length ? Integer.parseInt(opInput[pos+3]) : opInput.length -1;
            int src1 = pos+1 < opInput.length ? Integer.parseInt(opInput[pos+1]) : opInput.length -1;
            int src2 = pos+2 < opInput.length ? Integer.parseInt(opInput[pos+2]) : opInput.length -1;

            int val1;
            int val2;

            String srcPrint1 = "";
            String srcPrint2 = "";



            switch (opCode) {
                case 1: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                        srcPrint1 = "[" + src1 + "]";
                    } else {
                        val1 = src1;
                        srcPrint1 = val1 + "";
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                        srcPrint2 = "[" + src2 + "]";
                    } else {
                        val2 = src2;
                        srcPrint2 = val2 + "";
                    }
                    opInput[dest] = "" + (val1 + val2);

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = " + srcPrint1 + " + " + srcPrint2);
                        System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = " + val1 + " + " + val2);
                        System.out.println(":: [" + pos + "] :: ADD :: [" + dest + "] = " + opInput[dest]);
                        Tools.printIntArray(opInput);
                    }
                    pos+=4;
                    break;
                }
                case 2: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                        srcPrint1 = "[" + src1 + "]";
                    } else {
                        val1 = src1;
                        srcPrint1 = val1 + "";
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                        srcPrint2 = "[" + src2 + "]";
                    } else {
                        val2 = src2;
                        srcPrint2 = val2 + "";
                    }
                    opInput[dest] = "" + (val1 * val2);

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = " + srcPrint1 + " + " + srcPrint2);
                        System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = " + val1 + " * " + val2);
                        System.out.println(":: [" + pos + "] :: MULTIPLICATION :: [" + dest + "] = " + opInput[dest]);
                        Tools.printIntArray(opInput);
                    }
                    pos+=4;
                    break;
                }
                case 3: {
                    dest = Integer.parseInt(opInput[pos+1]);
                    opInput[dest] = inputVal;

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: INPUT :: input = " + inputVal);
                        System.out.println(":: [" + pos + "] :: INPUT :: [" + dest + "] = " + opInput[dest]);
                        Tools.printIntArray(opInput);
                    }
                    pos+=2;
                    break;
                }
                case 4: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                    } else {
                        val1 = src1;
                    }
                    outputVal = outputVal + " / " + val1;

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: OUTPUT :: output = [" + src1 + "]");
                        System.out.println(":: [" + pos + "] :: OUTPUT :: output = " + outputVal);
                        Tools.printIntArray(opInput);
                    }
                    pos+=2;
                    break;
                }
                case 5: {

                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                    } else {
                        val1 = src1;
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                    } else {
                        val2 = src2;
                    }

                    if (val1 != 0) {
                        dest = val2;
                        srcPrint1 = "--> [JUMP " + dest + "]";
                    } else {
                        dest = pos+3;
                        srcPrint1 = "--> [REGULAR " + dest + "]";
                    }

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: JUMP-IF-TRUE :: [" + pos + "] " + srcPrint1);
                        Tools.printIntArray(opInput);
                    }
                    pos=dest;
                    break;
                }
                case 6: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                    } else {
                        val1 = src1;
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                    } else {
                        val2 = src2;
                    }

                    if (val1 == 0) {
                        dest = val2;
                        srcPrint1 = "--> [JUMP " + dest + "]";
                    } else {
                        dest = pos+3;
                        srcPrint1 = "--> [REGULAR " + dest + "]";
                    }

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: JUMP-IF-FALSE :: [" + pos + "] " + srcPrint1);
                        Tools.printIntArray(opInput);
                    }
                    pos=dest;
                    break;
                }
                case 7: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                        srcPrint1 = "[" + src1 + "]";
                    } else {
                        val1 = src1;
                        srcPrint1 = val1 + "";
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                        srcPrint2 = "[" + src2 + "]";
                    } else {
                        val2 = src2;
                        srcPrint2 = val2 + "";
                    }

                    opInput[dest] = val1 < val2 ? "1" : "0";

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: LESS-THAN :: [" + dest + "] = " + srcPrint1 + " < " + srcPrint2);
                        System.out.println(":: [" + pos + "] :: LESS-THAN :: [" + dest + "] = " + (val1 < val2));
                        System.out.println(":: [" + pos + "] :: LESS-THAN :: [" + dest + "] = " + opInput[dest]);
                        Tools.printIntArray(opInput);
                    }
                    pos+=4;
                    break;
                }
                case 8: {
                    if (modeParam1 == 0) {
                        val1 = Integer.parseInt(opInput[src1]);
                        srcPrint1 = "[" + src1 + "]";
                    } else {
                        val1 = src1;
                        srcPrint1 = val1 + "";
                    }

                    if (modeParam2 == 0) {
                        val2 = Integer.parseInt(opInput[src2]);
                        srcPrint2 = "[" + src2 + "]";
                    } else {
                        val2 = src2;
                        srcPrint2 = val2 + "";
                    }

                    opInput[dest] = val1 == val2 ? "1" : "0";

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: EQUALS :: [" + dest + "] = " + srcPrint1 + " == " + srcPrint2);
                        System.out.println(":: [" + pos + "] :: EQUALS :: [" + dest + "] = " + (val1 == val2));
                        System.out.println(":: [" + pos + "] :: EQUALS :: [" + dest + "] = " + opInput[dest]);
                        Tools.printIntArray(opInput);
                    }
                    pos+=4;
                    break;
                }
                case 99: {

                    if (Properties.print) {
                        System.out.println(":: [" + pos + "] :: HALT");
                    }
                    halt = true;
                    break;
                }
                default: {
                    System.out.println(":: Something went wrong at [" + pos + "]=" + opCode);
                    halt = true;
                    break;
                }
            }
            halt = halt || pos >= opInput.length;
        }

        return outputVal;
    }
}
