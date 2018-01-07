package com.example.hispeed.calculatoroil;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hispeed on 26/7/2560.
 */

public class DetailCar extends AppCompatActivity {


    String[] type_car = {"ยี่ห้อรถยนต์", "Bmw", "Chevrolet", "Ford", "Foton", "Honda", "Isuzu", "Mazda", "Mercedes-Benz", "Mg", "Mini", "Mitsubishi", "Nissan", "Suzuki", "Tata", "Toyota"};

    String[] bmw = {"รุ่น", "118i M Sport", "118i Sport", "318i", "320d", "320d Celebration Edition", "320d GT Celebration Edition", "320d GT Luxury", "320d GT Sport", "320d Luxury",
            "320d M Performance", "320d Sport", "320i", "320i Luxury", "320i M Sport", "320i Sport", "328i M Sport", "330i M Sport", "520d", "520d M Sport",
            "520d Sport", "525d Celebration Edition", "525d Luxury", "525d M Sport", "528i Luxury", "730Ld M Sport", "730Ld Pure Excellence", "X1 sDrive 18i", "X1 sDrive 18i xLine", "X1 sDrive18d M Sport",
            "X1 sDrive18d xLine", "X1 sDrive18i M Sport", "X1 sDrive18i xLine", "X3 xDrive20d", "X3 xDrive20d Celebration Edition", "X3 xDrive20d Highline", "X3 xDrive20d M Sport", "X3 xDrive20i", "X4 xDrive 20d M Sport", "X4 xDrive20i M Sport",
            "X5 sDrive25d", "X5 sDrive25d Celebration Edition", "X5 sDrive25d Pure Experience", "X5 xDrive30d M Sport"};

    double[] bmw_combined = {0, 20, 20, 20, 27, 27, 27, 27, 27, 27,
            27, 27, 16.1, 16.1, 11.9, 16.1, 14.3, 16.1, 19.2, 19.2,
            20, 17.9, 17.9, 17.9, 14.3, 18.2, 18.2, 10.5, 10.5, 20.4,
            20.4, 10.5, 15.9, 17.9, 17.9, 17.9, 17.9, 12, 17.9, 12.3,
            18.2, 18.2, 18.2, 15.2};

    double[] bmw_town = {0, 16.1, 16.1, 16.1, 23.3, 23.3, 23.3, 23.3, 23.3, 23.3,
            23.3, 23.3, 13.2, 13.2, 9.1, 13.2, 10.8, 12.5, 15.9, 15.9,
            17.5, 14.5, 14.5, 14.5, 10.8, 15.4, 15.4, 8.1, 8.1, 18.2,
            18.2, 8.1, 13.3, 15.6, 15.6, 15.6, 15.6, 9.8, 15.6, 10,
            16.4, 16.4, 16.4, 13};

    double[] bmw_countryside = {0, 23.3, 23.3, 23.3, 29.4, 29.4, 29.4, 29.4, 29.4, 29.4,
            29.4, 29.4, 18.9, 18.9, 14.7, 18.9, 17.5, 19.2, 22.2, 22.2,
            21.7, 20.4, 20.4, 20.4, 17.5, 20, 20, 12.8, 12.8, 22.2,
            22.2, 12.8, 17.9, 19.2, 19.2, 19.2, 19.2, 14.1, 19.2, 14.3,
            19.2, 19.2, 19.2, 16.7};

    String[] chevrolet = {"รุ่น", "CAPTIVA 2.0 DSL AWD LTZ", "CAPTIVA 2.0 DSL FWD LSX", "CAPTIVA 2.0 DSL FWD LTZ", "CAPTIVA 2.0L DSL AWD AT LTZ", "CAPTIVA 2.0L DSL FWD AT LSX",
            "CAPTIVA 2.0L DSL FWD AT LTZ", "CAPTIVA 2.4 GAS AWD LTZ", "CAPTIVA 2.4 GAS FWD LSX", "CAPTIVA 2.4L GAS AWD AT LTZ", "CAPTIVA 2.4L GAS FWD AT LSX",
            "COLORADO C-CAB 2.5 MT LT", "COLORADO C-CAB 2.5 MT LTZ Z71", "COLORADO C-CAB 2.5L FGT 4x2 MT LS", "COLORADO C-CAB 2.5L FGT 4x2 MT LT", "COLORADO C-CAB 2.5L VGT 4x2 AT High Country",
            "COLORADO C-CAB 2.5L VGT 4x2 AT High Country STORM", "COLORADO C-CAB 2.5L VGT 4x2 AT LT", "COLORADO C-CAB 2.5L VGT 4x2 AT LTZ", "COLORADO C-CAB 2.5L VGT 4x2 MT LT", "COLORADO C-CAB 2.5L VGT 4x2 MT LTZ",
            "COLORADO C-CAB 2.5L VGT 4x4 AT High Country", "COLORADO C-CAB 2.5L VGT 4x4 AT High Country STORM", "COLORADO C-CAB 2.8 AT 4x2 HIGH COUNTRY", "COLORADO C-CAB 2.8 AT 4x2 HIGH COUNTRY STORM", "COLORADO C-CAB 2.8 AT HIGH COUNTRY 4WD",
            "COLORADO C-CAB 2.8 AT LT Z71", "COLORADO C-CAB 2.8 AT LTZ Z71", "COLORADO FLAT DECK 2.5 MT LS", "COLORADO FLATDECK 2.5L FGT 4x2 MT LS", "COLORADO S-CAB 2.5 MT LS",
            "COLORADO S-CAB 2.5L FGT 4x2 MT LS", "COLORADO X-CAB 2.5 MT LS", "COLORADO X-CAB 2.5 MT LT", "COLORADO X-CAB 2.5 MT LT Z71", "COLORADO X-CAB 2.5 MT LTZ Z71",
            "COLORADO X-CAB 2.5L FGT 4x2 MT LS", "COLORADO X-CAB 2.5L FGT 4x2 MT LT", "COLORADO X-CAB 2.5L VGT 4x2 AT LTZ", "COLORADO X-CAB 2.5L VGT 4x2 MT LT", "COLORADO X-CAB 2.5L VGT 4x2 MT LTZ",
            "CRUZE 1.8 AT LT", "CRUZE 1.8 AT LTZ", "TRAILBLAZER 2.5 MT LT", "TRAILBLAZER 2.5L VGT 4x2 AT LT", "TRAILBLAZER 2.5L VGT 4x2 AT LTZ",
            "TRAILBLAZER 2.5L VGT 4x4 AT LTZ", "TRAILBLAZER 2.8 AT LT", "TRAILBLAZER 2.8 AT LTZ", "TRAILBLAZER 2.8 AT LTZ1 4WD"};

    double[] chevrolet_combined = {0, 11.6, 13.5, 13.5, 11.6, 13.5, 13.5, 9.8, 10.3, 9.8, 10.3,
            13.3, 13.9, 13.5, 13.5, 12.2, 12.2, 12.2, 12.2, 14.3, 14.3,
            12.2, 12.2, 11, 11, 10.9, 11.4, 11.2, 13.3, 13.5, 13.3,
            13.5, 13.3, 13.3, 13.3, 13.3, 13.5, 13.5, 12.2, 14.3, 14.3,
            12.8, 12.8, 13.5, 12, 13.2, 12, 10.9, 10.9, 9.9,};

    double[] chevrolet_town = {0, 8.8, 10.5, 10.5, 8.8, 10.5, 10.5, 7.5, 7.6, 7.5, 7.6,
            10.6, 11.2, 11.1, 11.1, 9.8, 9.8, 9.8, 9.8, 11.9, 11.9,
            9.8, 9.8, 8.8, 8.8, 7.9, 8.8, 8.8, 10.6, 11.1, 10.6,
            11.1, 10.6, 10.6, 10.6, 10.6, 11.1, 11.1, 9.8, 11.9, 11.9,
            9.4, 9.4, 11.4, 9.4, 10.3, 9.4, 7.9, 7.9, 7.8};

    double[] chevrolet_countryside = {0, 14.1, 16.1, 16.1, 14.1, 16.1, 16.1, 12, 12.8, 12, 12.8,
            15.6, 15.9, 15.6, 15.6, 14.3, 14.3, 14.3, 14.3, 16.1, 16.1,
            14.3, 14.3, 12.8, 12.8, 13, 13.5, 13.3, 15.6, 15.6, 15.6,
            15.6, 15.6, 15.6, 15.6, 15.6, 15.6, 15.6, 14.3, 16.1, 16.1,
            16.4, 16.4, 15.4, 14.3, 15.9, 14.3, 13, 13, 11.8};

    String[] suzuki = {"รุ่น", "CELERIO GA 1.0L MT", "CELERIO GL 1.0L CVT", "CELERIO GLX 1.0L CVT", "CIAZ GA MT", "CIAZ GL CVT",
            "CIAZ GLX CVT", "CIAZ RS CVT", "SWIFT GA 1.25L 5MT", "SWIFT GA 1.25L CVT", "SWIFT GLX 1.25L CVT",
            "SWIFT RX 1.25L CVT", "SWIFT RX-II 1.25L CVT", "SWIFT SAI 1.25L CVT"};

    double[] suzuki_combined = {0, 21.3, 20.8, 20.8, 20, 20, 20, 20, 20, 20.4, 20.4,
            20.4, 20.4, 20.4};

    double[] suzuki_town = {0, 17.2, 17.2, 17.2, 16.1, 16.1, 16.1, 16.1, 16.1, 16.7, 16.7,
            16.7, 16.7, 16.7};

    double[] suzuki_countryside = {0, 24.4, 23.8, 23.8, 23.3, 23.3, 23.3, 23.3, 23.8, 22.7, 22.7,
            22.7, 22.7, 22.7};

    String[] toyota = {"รุ่น", "Avanza 1.5E A/T", "Avanza 1.5E M/T", "Avanza 1.5G A/T", "Avanza 1.5S A/T", "Camry 2.0G",
            "Camry 2.0G Extremo", "Camry 2.5G", "Camry HV Navigator", "Camry HV Premium", "Corolla 1.6E CNG",
            "Corolla 1.6G", "Corolla 1.6J", "Corolla 1.8E", "Corolla 1.8V Navi", "Corolla Esport",
            "Corolla Esport Nurburgring Edition", "Corolla Esport Option", "FORTUNER 2.4G MT", "FORTUNER 2.4V", "FORTUNER 2.7V",
            "FORTUNER 2.8 TRD Sportivo", "FORTUNER 2.8 TRD Sportivo 4WD", "FORTUNER 2.8V", "FORTUNER 4x4 2.8V", "HILUX REVO Double Cab 2.4E",
            "HILUX REVO Double Cab 2.4J Plus", "HILUX REVO Double Cab 2.7E", "HILUX REVO Double Cab 4x4 2.4E Plus", "HILUX REVO Double Cab 4x4 2.8G", "HILUX REVO Double Cab 4x4 2.8G AT",
            "HILUX REVO Double Cab Prerunner 2.4 TRD Sportivo", "HILUX REVO Double Cab Prerunner 2.4 TRD Sportivo AT", "HILUX REVO Double Cab Prerunner 2.4E", "HILUX REVO Double Cab Prerunner 2.4E AT", "HILUX REVO Double Cab Prerunner 2.4E Plus",
            "HILUX REVO Double Cab Prerunner 2.4E Plus AT", "HILUX REVO Double Cab Prerunner 2.4G", "HILUX REVO Double Cab Prerunner 2.4G AT", "HILUX REVO Double Cab Prerunner 2.4G Plus AT", "HILUX REVO Double Cab Prerunner 2.4J Plus",
            "HILUX REVO Double Cab Prerunner 2.7E AT", "HILUX REVO Double Cab Prerunner 2.8G AT", "HILUX REVO Smart Cab 2.4 TRD Sportivo", "HILUX REVO Smart Cab 2.4E", "HILUX REVO Smart Cab 2.4G",
            "HILUX REVO Smart Cab 2.4J", "HILUX REVO Smart Cab 2.4J Plus", "HILUX REVO Smart Cab 4x4 2.4E Plus", "HILUX REVO Smart Cab 4x4 2.8G", "HILUX REVO Smart Cab Prerunner 2.4 TRD Sportivo",
            "HILUX REVO Smart Cab Prerunner 2.4 TRD Sportivo AT", "HILUX REVO Smart Cab Prerunner 2.4E", "HILUX REVO Smart Cab Prerunner 2.4E Plus", "HILUX REVO Smart Cab Prerunner 2.4E Plus AT", "HILUX REVO Smart Cab Prerunner 2.4G",
            "HILUX REVO Smart Cab Prerunner 2.4G AT", "HILUX REVO Smart Cab Prerunner 2.4J Plus", "HILUX REVO Smart Cab 4x4 2.4E Plus", "HILUX REVO Smart Cab 4x4 2.8G", "HILUX REVO Smart Cab Prerunner 2.4 TRD Sportivo",
            "HILUX REVO Smart Cab Prerunner 2.4 TRD Sportivo AT", "HILUX REVO Smart Cab Prerunner 2.4E", "HILUX REVO Smart Cab Prerunner 2.4E Plus", "HILUX REVO Smart Cab Prerunner 2.4E Plus AT", "HILUX REVO Smart Cab Prerunner 2.4G",
            "HILUX REVO Smart Cab Prerunner 2.4G AT", "HILUX REVO Smart Cab Prerunner 2.4J Plus", "HILUX REVO Smart Cab Prerunner 2.7E", "HILUX REVO Smart Cab Prerunner 2.8G", "HILUX REVO Smart Cab Preruuner 2.4E AT",
            "HILUX REVO Standard Cab 2.4J", "HILUX REVO Standard Cab 2.4J Plus SWB", "HILUX REVO Standard Cab 2.7J", "HILUX REVO STANDARD CAB 2.8J Plus", "HILUX REVO Standard Cab 4x4 2.8J",
            "Prius Standard grade", "Prius Top grade", "Vios 1.5E CVT", "Vios 1.5E CVT Ivory", "Vios 1.5G CVT",
            "Vios 1.5G CVT Ivory", "Vios 1.5J CVT", "Vios 1.5S CVT", "YARIS 1.2E CVT", "YARIS 1.2G CVT",
            "YARIS 1.2J CVT", "YARIS 1.2J ECO CVT", "Yaris TRD Sportivo"};

    double[] toyota_combined = {0, 14.7, 14.9, 14.7, 14.7, 13.2, 13.2, 12.5, 17.9, 17.9, 16.1,
            16.4, 15.9, 15.9, 15.9, 15.9, 15.6, 15.9, 14.7, 13.3, 9.5,
            14.3, 13.9, 14.3, 13.9, 14.5, 14.5, 9.3, 13.7, 13.3, 13.7,
            14.3, 13.2, 14.3, 13.2, 14.3, 13.2, 14.3, 13.2, 14.3, 14.3,
            9.3, 14.3, 14.5, 14.5, 14.5, 14.5, 14.5, 13.7, 13.3, 14.3,
            13.2, 14.3, 14.3, 13.2, 14.3, 13.2, 14.3, 13.7, 13.3, 14.3,
            13.2, 14.3, 14.3, 13.2, 14.3, 13.2, 14.3, 9.3, 14.1, 13.2,
            14.5, 14.5, 9.3, 14.5, 13.3, 24.4, 24.4, 17.2, 17.2, 17.2,
            17.2, 17.2, 17.2, 20, 20, 20, 20, 20};

    double[] toyota_town = {0, 11.8, 11.8, 11.8, 11.8, 9.6, 9.6, 9, 20.4, 20.4, 12.2,
            12.5, 12, 12, 12, 12, 11.8, 12, 12.2, 10.5, 7.2,
            12.3, 11.9, 12.3, 11.9, 11.6, 11.6, 7.1, 11.4, 10.9, 11.8,
            11.9, 10.2, 11.9, 10.2, 11.9, 10.2, 11.9, 10.2, 12.3, 11.9,
            7.2, 12.2, 11.6, 11.6, 11.6, 11.6, 11.6, 11.4, 10.9, 11.9,
            10.2, 11.9, 11.9, 10.2, 11.9, 10.2, 11.9, 11.4, 10.9, 11.9,
            10.2, 11.9, 11.9, 10.2, 11.9, 10.2, 11.9, 7.1, 11.4, 10.2,
            11.6, 11.6, 7.1, 11.5, 10.9, 35.7, 35.7, 13.7, 13.7, 13.7,
            13.7, 13.7, 13.7, 16.7, 16.7, 16.7, 16.7, 16.7};

    double[] toyota_countryside = {0, 17.2, 17.5, 17.2, 17.2, 16.7, 16.7, 16.4, 16.7, 16.7, 20,
            20.4, 19.6, 19.2, 19.2, 19.2, 19.6, 19.2, 16.9, 15.6, 11.6,
            15.9, 15.4, 15.9, 15.4, 16.7, 16.7, 11.2, 15.6, 15.4, 15.2,
            16.4, 16.1, 16.4, 16.1, 16.4, 16.1, 16.4, 16.1, 15.6, 16.4,
            11.2, 15.6, 16.7, 16.7, 16.7, 16.7, 16.7, 15.6, 15.4, 16.4,
            16.1, 16.4, 16.4, 16.1, 16.4, 16.1, 16.4, 15.6, 15.4, 16.4,
            16.1, 16.4, 16.4, 16.1, 16.4, 16.1, 16.4, 11.2, 16.1, 16.1,
            16.7, 16.7, 11.6, 16.9, 15.4, 20.8, 20.8, 20.4, 20.4, 20.4,
            20.4, 20.4, 20.4, 22.2, 22.2, 22.2, 22.2, 22.2};

    String[] nissan = {"รุ่น", "ALMERA 1.2L E CVT", "ALMERA 1.2L E MT", "ALMERA 1.2L E SPORTECH CVT", "ALMERA 1.2L EL CVT", "ALMERA 1.2L NISMO E CVT",
            "ALMERA 1.2L NISMO EL CVT", "ALMERA 1.2L NISMO VL CVT", "ALMERA 1.2L S MT", "ALMERA 1.2L V CVT", "ALMERA 1.2L V SPORTECH CVT",
            "ALMERA 1.2L VL CVT", "ALMERA 1.2L VL SPORTECH CVT", "MARCH 1.2L E CVT", "MARCH 1.2L E MT", "MARCH 1.2L EL CVT",
            "MARCH 1.2L S MT", "MARCH 1.2L V CVT", "MARCH 1.2L VL CVT", "NAVARA DOUBLE CAB 4WD S 6MT", "NAVARA DOUBLE CAB 4WD VL 7AT",
            "NAVARA DOUBLE CAB 4WD VL 7AT Sportech", "NAVARA DOUBLE CAB Calibre E 6MT", "NAVARA DOUBLE CAB Calibre EL 6MT", "NAVARA DOUBLE CAB Calibre EL 6MT Sportech", "NAVARA DOUBLE CAB Calibre EL 7AT",
            "NAVARA DOUBLE CAB Calibre S 6MT", "NAVARA DOUBLE CAB Calibre V 7AT", "NAVARA DOUBLE CAB Calibre VL 6MT", "NAVARA DOUBLE CAB E 6MT", "NAVARA DOUBLE CAB S 6MT",
            "NAVARA KING CAB 4WD V 6MT", "NAVARA KING CAB Calibre E 6MT", "NAVARA KING CAB Calibre EL 6MT", "NAVARA KING CAB Calibre S 6MT", "NAVARA KING CAB Calibre V 7AT",
            "NAVARA KING CAB E 6MT", "NAVARA KING CAB S 6MT", "NAVARA KING CAB V 6MT", "NAVARA SINGLE CAB S 6MT", "NAVARA SINGLE CAB SL 6MT",
            "NOTE 1.2 V", "NOTE 1.2 VL", "NP300 NAVARA CHASSIS CAB", "NP300 NAVARA DOUBLE CAB 4WD S 6MT", "NP300 NAVARA DOUBLE CAB 4WD Sportech VL 7AT",
            "NP300 NAVARA DOUBLE CAB 4WD VL 7AT", "NP300 NAVARA DOUBLE CAB Calibre E 6MT", "NP300 NAVARA DOUBLE CAB Calibre EL 6MT", "NP300 NAVARA DOUBLE CAB Calibre S 6MT", "NP300 NAVARA DOUBLE CAB Calibre Sportech EL 6MT",
            "NP300 NAVARA DOUBLE CAB Calibre Sportech VL 6MT", "NP300 NAVARA DOUBLE CAB Calibre V 7AT", "NP300 NAVARA DOUBLE CAB E 6MT", "NP300 NAVARA KING CAB 4WD V 6MT", "NP300 NAVARA KING CAB Calibre E 6MT",
            "NP300 NAVARA KING CAB Calibre EL 6MT", "NP300 NAVARA KING CAB Calibre S 6MT", "NP300 NAVARA KING CAB Calibre Sportech EL 6MT", "NP300 NAVARA KING CAB Calibre V 7AT", "NP300 NAVARA KING CAB E 6MT",
            "NP300 NAVARA KING CAB S 6MT", "NP300 NAVARA KING CAB V 6MT", "NP300 NAVARA SINGLE CAB S 6MT", "NP300 NAVARA SINGLE CAB SL 6MT", "PULSAR 1.8L V SUNROOF NAVI CVT",
            "SYLPHY 1.6 DIG TURBO CVT", "SYLPHY 1.6 E CVT", "SYLPHY 1.6 SV CVT", "SYLPHY 1.6 V CVT", "TEANA 2.0L XE CVT",
            "TEANA 2.0L XL CVT", "TEANA 2.0L XL NAVI CVT", "TEANA 2.5L XV CVT", "TEANA 2.5L XV NAVI CVT", "X-TRAIL 2.0L S CVT",
            "X-TRAIL 2.0L V CVT", "X-TRAIL 2.5L V CVT", "X-TRAIL HEV 2.0L E 2WD CVT", "X-TRAIL HEV 2.0L S 2WD CVT", "X-TRAIL HEV 2.0L V 4WD CVT"};

    double[] nissan_combined = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
            20, 20, 20, 20, 20, 20, 20, 20, 13.7, 12.8,
            12.7, 13.9, 13.9, 13.7, 13.2, 13.9, 13.2, 13.5, 13.9, 13.9,
            13.5, 13.9, 13.9, 13.9, 13.2, 13.9, 13.9, 13.9, 13.9, 13.9,
            20, 20, 13.9, 13.7, 12.7, 12.8, 13.9, 13.9, 13.9, 13.7,
            13.5, 13.2, 13.9, 13.5, 13.9, 13.9, 13.9, 13.7, 13.2, 13.9,
            13.9, 13.9, 13.9, 13.9, 12.2, 12.8, 15.9, 15.9, 15.9, 13.5,
            13.5, 13.5, 13.3, 13.3, 13.3, 13.3, 12, 15.9, 15.9, 15.6};

    double[] nissan_town = {0, 16.7, 15.6, 16.7, 16.7, 16.7, 16.7, 16.7, 15.6, 16.7, 16.7,
            16.7, 16.7, 15.9, 15.6, 15.9, 15.6, 15.9, 15.9, 11.6, 10.1,
            9.8, 11.6, 11.6, 11.4, 10, 11.6, 10, 11.5, 11.6, 11.6,
            11.5, 11.6, 11.6, 11.6, 10, 11.6, 11.6, 11.6, 11.8, 11.8,
            16.4, 16.4, 11.8, 11.6, 9.8, 10.1, 11.6, 11.6, 11.6, 11.4,
            11.5, 10, 11.6, 11.5, 11.6, 11.6, 11.6, 11.4, 10, 11.6,
            11.6, 11.6, 11.8, 11.8, 8.3, 9.8, 12.3, 12.3, 12.3, 10.1,
            10.1, 10.1, 9.9, 9.9, 10.6, 10.6, 8.8, 13.9, 13.9, 13.5};

    double[] nissan_countryside = {0, 22.2, 23.3, 22.2, 22.2, 22.2, 22.2, 22.2, 23.3, 22.2, 22.2,
            22.2, 22.2, 23.3, 23.3, 23.3, 23.3, 23.3, 23.3, 15.2, 15.4,
            14.9, 15.9, 15.9, 15.4, 16.4, 15.9, 16.4, 15.2, 15.9, 15.9,
            14.9, 15.9, 15.9, 15.9, 16.4, 15.9, 15.9, 15.9, 15.6, 15.6,
            22.2, 22.2, 15.6, 15.2, 14.9, 15.4, 15.9, 15.9, 15.9, 15.4,
            15.2, 16.4, 15.9, 14.9, 15.9, 15.9, 15.9, 15.4, 16.4, 15.9,
            15.9, 15.9, 15.6, 15.6, 16.9, 15.6, 18.9, 18.9, 18.9, 16.7,
            16.7, 16.7, 16.7, 16.7, 15.6, 15.6, 15.2, 17.5, 17.5, 17.2};

    String[] ford = {"รุ่น", "EcoSport 1.5L Ambiente AT", "EcoSport 1.5L Titanium AT", "EcoSport 1.5L Trend AT", "EcoSport 1.5L Trend Black Edition AT", "Everest 2.2L AT 4x2 Titanium",
            "Everest 3.2L AT 4x4 Titanium", "Fiesta 5-door Hatchback 1.5L AT", "Fiesta 5Dr 1.5L Sport AT", "Fiesta 5Dr 1.5L Trend AT", "Focus Trend+",
            "New Everest 2.2L Titanium 4x2", "New Everest 3.2L Titanium 4x4", "New Fiesta 4Dr 1.5L AT Titanium", "New Fiesta EcoBoost Turbo AT Sport", "New Fiesta EcoBoost Turbo AT Titanium",
            "New Focus 1.5L EcoBoost Turbo Sport AT", "New Focus 1.5L EcoBoost Turbo Titanium AT", "New Ranger 2.2 HP AT Open Cab 4x2 Hi-Rider Wildtrak", "New Ranger 2.2L HP AT DBL 4x2 Hi-Rider Wildtrak", "New Ranger 2.2L HP AT DBL 4x2 Hi-Rider XLT",
            "New Ranger 2.2L HP AT DBL 4x4 Wildtrak", "New Ranger 2.2L HP AT Open Cab 4x2 Hi-Rider XLT", "New Ranger 2.2L HP MT DBL 4x2 Hi-Rider Wildtrak", "New Ranger 2.2L HP MT DBL 4x2 Hi-Rider XLT", "New Ranger 2.2L HP MT DBL 4x4 XLT",
            "New Ranger 2.2L HP MT Open Cab 4x2 Hi-Rider XLT", "New Ranger 2.2L HP MT Open Cab 4x4 XLT", "New Ranger 2.2L MP MT DBL 4x2 Hi-Rider XLS", "New Ranger 2.2L MP MT Open Cab 4x2 Hi-Rider XLS", "New Ranger 2.2L MP MT Open Cab 4x2 XL",
            "New Ranger 2.2L MP MT Open Cab 4x2 XLS", "New Ranger 2.2L MP MT Standard Cab 4X2 Base", "New Ranger 3.2L AT DBL 4x4 Wildtrak", "New Ranger 3.2L AT DBL 4x4 XLT", "New Ranger 3.2L MT Open Cab 4x4 XLT",
            "New Ranger SWB 2.2L 4x2", "New Ranger SWB 3.2L 4x4", "Ranger (SWB) 2.2L MT 4x2", "Ranger (SWB) 3.2L AT 4x4", "Ranger 2.2L Hi-Power AT Double Cab 4x2 Hi-Rider FX4",
            "Ranger 2.2L Hi-Power AT Open Cab 4x2 Hi-Rider XLS", "Ranger 2.2L Hi-Power MT Double Cab 4x2 Hi-Rider FX4", "Ranger 2.2L Hi-Power MT Single Cab 4x2 Base", "Ranger 2.2L Mid-Power MT Open Cab 4x2 Hi-Rider XL", "Ranger 2.2L Mid-Power MT Open Cab 4x2 XL",
            "Ranger 2.2L Mid-Power MT Open Cab 4x4 Hi-Rider XLS"};

    double[] ford_combined = {0, 15.4, 15.4, 15.4, 15.4, 13.2, 11.4, 17.5, 17.5, 17.5, 14.3,
            13.2, 11.4, 16.4, 18.9, 18.2, 14.3, 14.3, 13.3, 13.3, 13.3,
            12.2, 13.3, 14.5, 14.5, 13, 14.5, 13, 14.1, 14.1, 14.1,
            14.1, 14.1, 11, 11, 11, 13.7, 9.8, 13.7, 9.8, 13.3,
            13.2, 14.5, 13.2, 14.1, 14.1, 13.9};

    double[] ford_town = {0, 12.2, 12.2, 12.2, 12.2, 10.4, 8.5, 13.3, 13.3, 13.3, 9.9,
            10.4, 8.5, 11.9, 14.1, 13.7, 9.9, 9.9, 11, 11, 11,
            10, 11, 12.7, 12.7, 11.4, 12.7, 11.4, 12.3, 12.5, 12.5,
            12.5, 12.5, 8.6, 8.6, 8.9, 12.2, 7.8, 12.2, 7.8, 11,
            11, 12.7, 11.4, 12.5, 12.5, 12.3};

    double[] ford_countryside = {0, 18.2, 18.2, 18.2, 18.2, 15.4, 13.9, 21.3, 21.3, 21.3, 19.2,
            15.4, 13.9, 20.8, 23.8, 22.7, 19.2, 19.2, 15.4, 15.4, 15.4,
            14.1, 15.4, 15.9, 15.9, 14.3, 15.9, 14.3, 15.6, 15.4, 15.4,
            15.4, 15.4, 13, 13, 12.8, 14.7, 11.5, 14.7, 11.5, 15.4,
            14.9, 15.9, 14.5, 15.4, 15.4, 14.9};

    String[] mazda = {"รุ่น", "BT-50 PRO : DBL 2.2 Hi-Racer", "BT-50 PRO : DBL 2.2 Hi-Racer ABS", "BT-50 PRO : DBL 2.2 Hi-Racer ABS AT", "BT-50 PRO : DBL 2.2 S", "BT-50 PRO : DBL 2.2 V ABS",
            "BT-50 PRO : DBL 4x4 3.2 R ABS/DSC/Leather", "BT-50 PRO : FSC 2.2 Hi-Racer", "BT-50 PRO : FSC 2.2 S", "BT-50 PRO : FSC 2.2 V", "BT-50 PRO : STD 2.2 S",
            "CX-3 : 1.5 XDL", "CX-3 : 2.0 C", "CX-3 : 2.0 E", "CX-3 : 2.0 S", "CX-3 : 2.0 SP",
            "CX-5 : 2.0 C", "CX-5 : 2.0 S", "CX-5 : XD", "CX-5 : XDL", "Mazda 2 : 1.3 High",
            "Mazda 2 : 1.3 High Connect", "Mazda 2 : 1.3 High Plus", "Mazda 2 : 1.3 Sports High", "Mazda 2 : 1.3 Sports High Connect", "Mazda 2 : 1.3 Sports High Plus",
            "Mazda 2 : 1.3 Sports Standard", "Mazda 2 : 1.3 Standard", "Mazda 2 : XD", "Mazda 2 : XD High Connect", "Mazda 2 : XD High Plus L",
            "Mazda 2 : XD Sports", "Mazda 2 : XD Sports High Connect", "Mazda 2 : XD Sports High Plus L", "Mazda 3 : 2.0 C", "Mazda 3 : 2.0 C Sports",
            "Mazda 3 : 2.0 E", "Mazda 3 : 2.0 E Sports", "Mazda 3 : 2.0 S", "Mazda 3 : 2.0 S Sports", "Mazda 3 : 2.0 SP",
            "Mazda 3 : 2.0 SP Sports"};

    double[] mazda_combined = {0, 13.2, 13.2, 10.1, 14.1, 14.1, 10.6, 13.3, 14.7, 14.7, 12.2,
            23.3, 16.4, 16.4, 16.4, 16.4, 14.5, 14.5, 17.5, 17.5, 23.3,
            23.3, 23.3, 23.3, 23.3, 23.3, 23.3, 23.3, 26.3, 26.3, 26.3,
            26.3, 26.3, 26.3, 15.6, 15.6, 15.6, 15.6, 15.6, 15.6, 15.6,
            15.6};

    double[] mazda_town = {0, 11.1, 11.1, 7.9, 11.6, 11.6, 8.3, 11.5, 11.9, 11.9, 10.4,
            20.8, 13, 13, 13, 13, 11.9, 11.9, 14.7, 14.9, 19.6,
            19.6, 19.6, 19.6, 19.6, 19.6, 19.6, 19.6, 22.2, 22.2, 22.2,
            22.2, 22.2, 22.2, 11.8, 11.8, 11.8, 11.8, 11.8, 11.8, 11.8,
            11.8};

    double[] mazda_countryside = {0, 14.7, 14.7, 11.9, 16.1, 16.1, 12.7, 14.9, 16.9, 16.9, 13.7,
            24.4, 19.6, 19.6, 19.6, 19.6, 16.7, 16.7, 19.6, 19.6, 26.3,
            26.3, 26.3, 26.3, 26.3, 26.3, 26.3, 26.3, 29.4, 29.4, 29.4,
            29.4, 29.4, 29.4, 19.2, 19.2, 19.2, 19.2, 19.2, 19.2, 19.2,
            19.2};

    String[] mitsubishi = {"รุ่น", "Attrage GLS (CVT)", "Attrage GLS-LTD (CVT)", "Attrage GLX (CVT)", "Attrage GLX (MT)", "Mirage GL",
            "Mirage GLS", "Mirage GLS Ltd.", "Mirage GLX (CVT)", "Mirage GLX (MT)", "Pajero Sport 2.4D GLS-LTD",
            "Pajero Sport 2.4D GT", "Pajero Sport 2.4D GT-Premium", "Pajero Sport 2.4D GT-Premium 4WD", "Space Wagon", "Triton Double Cab 2WD 2.5 GLX", "Triton Double Cab 4WD 2.4 GLS-LTD",
            "Triton Double Cab 4WD 2.4 GLS-LTD AT", "Triton Double Cab Plus 2.4 GLS-LTD", "Triton Double Cab Plus 2.4 GLS-LTD AT", "Triton Double Cab Plus 2.4 GLX", "Triton Mega Cab 2WD 2.4 GLX",
            "Triton Mega Cab 2WD 2.5 GL", "Triton Mega Cab 2WD 2.5 GLX", "Triton Mega Cab Plus 2.4 GLS-LTD", "Triton Mega Cab Plus 2.4 GLS-LTD AT", "Triton Mega Cab Plus 2.4 GLX",
            "Triton Single Cab 2.4 GL", "Triton Single Cab 2.5 GL", "Triton Single Cab 2.5 GL 4WD", "Triton Single Cab 2.5 GL 4WD SWB"};

    double[] mitsubishi_combined = {0, 23.3, 23.3, 23.3, 23.3, 23.3, 23.3, 23.3, 23.8, 23.3, 13.5,
            13.5, 13.3, 13.3, 0, 13.9, 15.2, 13.3, 14.9, 13.5, 15.2, 9.3,
            13.9, 13.9, 15.2, 13.7, 15.2, 9.3, 13.9, 12.8, 11.8};

    double[] mitsubishi_town = {0, 20.4, 20.4, 20.4, 19.6, 18.5, 20, 20, 20, 18.5, 11.1,
            11.1, 10.9, 10.9, 0, 10.8, 11.6, 11.1, 11.8, 11.4, 11.9, 7.1,
            10.8, 10.8, 11.9, 11.6, 11.9, 7.1, 10.8, 10.6, 9.6};

    double[] mitsubishi_countryside = {0, 25, 25, 25.6, 25.6, 27, 25.6, 25.6, 26.3, 27, 15.4,
            15.4, 15.2, 15.2, 0, 16.9, 17.2, 14.9, 17.5, 15.2, 17.9, 11.2,
            16.9, 16.9, 17.9, 15.4, 17.9, 11.2, 16.9, 14.5, 13.5};

    String[] isuzu = {"รุ่น", "D-MAX Cab4 1.9 Ddi S", "D-MAX Cab4 1.9 Ddi Z", "D-MAX Hi-Lander 2-Doors 1.9 Ddi L", "D-MAX Hi-Lander 2-Doors 1.9 Ddi X-Series", "D-MAX Hi-Lander 2-Doors 1.9 Ddi Z",
            "D-MAX Hi-Lander 2-Doors 1.9 Ddi Z A/T", "D-MAX Hi-Lander 2-Doors 1.9 Ddi Z DVD", "D-MAX Hi-Lander 2-Doors 1.9 Ddi Z-Prestige", "D-MAX Hi-Lander 2-Doors 1.9 Ddi Z-Prestige A/T", "D-MAX Hi-Lander 2-Doors 3.0 Ddi Z-Prestige",
            "D-MAX Hi-Lander 4-Doors 1.9 Ddi L", "D-MAX Hi-Lander 4-Doors 1.9 Ddi X-Series", "D-MAX Hi-Lander 4-Doors 1.9 Ddi X-Series A/T", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z A/T",
            "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z DVD", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z DVD Limited", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z-Prestige", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z-Prestige A/T", "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z-Prestige A/T Limited",
            "D-MAX Hi-Lander 4-Doors 1.9 Ddi Z-Prestige Limited", "D-MAX Hi-Lander 4-Doors 3.0 Ddi Z-Prestige", "D-MAX Hi-Lander 4-Doors 3.0 Ddi Z-Prestige A/T", "D-MAX Spacecab 1.9 Ddi L", "D-MAX Spacecab 1.9 Ddi S",
            "D-MAX Spacecab 1.9 Ddi X-Series Speed", "D-MAX Spacecab 1.9 Ddi Z", "D-MAX Spark 1.9 Ddi B", "D-MAX Spark 1.9 Ddi S", "D-MAX Spark 3.0 Ddi S",
            "D-MAX Spark Cab Chassis 1.9 Ddi B", "D-MAX Spark Flat Deck 1.9 Ddi B", "D-MAX V-Cross 2-Doors 3.0 Ddi Z", "D-MAX V-Cross 4-Doors 3.0 Ddi Z-Prestige", "mu-X 4x2 1.9 Ddi CD A/T",
            "mu-X 4x2 1.9 Ddi DA DVD NAVI A/T", "mu-X 4x2 1.9 Ddi DVD A/T", "mu-X 4x2 1.9 Ddi DVD M/T", "mu-X 4x2 3.0 Ddi DA DVD NAVI A/T"};

    double[] isuzu_combined = {0, 15.9, 15.9, 15.9, 15.9, 15.9, 15.2, 15.9, 15.9, 15.2, 14.1,
            14.9, 14.9, 15.2, 14.9, 15.2, 14.9, 14.9, 14.9, 15.2, 15.2,
            14.9, 13.5, 14.1, 15.9, 15.9, 15.9, 15.9, 16.1, 16.1, 14.1,
            16.1, 16.1, 13.3, 13.3, 14.3, 14.3, 14.3, 14.9, 13.9};

    double[] isuzu_town = {0, 12.8, 12.8, 12.8, 12.8, 12.8, 13.2, 12.8, 12.8, 13.2, 10.8,
            12.2, 12.2, 13.2, 12.2, 13.2, 12.2, 12.2, 12.2, 13.2, 13.2,
            12.2, 10.5, 11.6, 12.8, 12.8, 12.8, 12.8, 12.8, 12.8, 10.8,
            12.8, 12.8, 10.6, 10.6, 12.5, 12.5, 12.5, 12.3, 11.9};

    double[] isuzu_countryside = {0, 18.5, 18.5, 18.5, 18.5, 18.5, 16.4, 18.5, 18.5, 16.4, 17.2,
            17.2, 17.2, 16.4, 17.2, 16.4, 17.2, 17.2, 17.2, 16.4, 16.4,
            17.2, 16.4, 15.9, 18.5, 18.5, 18.5, 18.5, 19.2, 19.2, 17.2,
            19.2, 19.2, 15.9, 15.9, 15.6, 15.6, 15.6, 16.9, 15.6};

    String[] honda = {"รุ่น", "ACCORD 2.0 E", "ACCORD 2.0 EL", "ACCORD 2.4 EL", "ACCORD HYBRID", "ACCORD HYBRID TECH",
            "BRIO AMAZE SV CVT", "BRIO AMAZE V CVT", "BRIO V CVT", "BR-V SV", "BR-V V",
            "CITY S CNG CVT", "CITY S CNG MT", "CITY S CVT", "CITY S MT", "CITY SV CVT",
            "CITY V CNG CVT", "CITY V CVT", "CIVIC 1.8 E", "CIVIC 1.8 EL", "CIVIC HATCHBACK TURBO",
            "CIVIC TURBO", "CR-V 2.0 E 4WD", "CR-V 2.0 S", "CR-V 2.0 SE 4WD", "CR-V 2.4 E",
            "CR-V 2.4 EL", "CR-V 2.4 EL 4WD", "CR-V DT-E", "CR-V DT-EL 4WD", "HR-V E",
            "HR-V EL", "HR-V S", "JAZZ RS CVT", "JAZZ S CVT", "JAZZ S MT",
            "JAZZ SV CVT", "JAZZ V CVT", "MOBILIO RS", "MOBILIO RS CVT", "MOBILIO S",
            "MOBILIO S CVT", "MOBILIO S MT", "MOBILIO V", "MOBILIO V CVT", "ODYSSEY 2.4 EL"};

    double[] honda_combined = {0, 12.8, 12.8, 12.2, 23.8, 23.8, 20, 20, 20, 16.4, 16.4,
            17.9, 17.2, 17.9, 18.2, 17.9, 17.9, 17.9, 16.1, 16.1, 17.2,
            17.9, 12.2, 12.2, 12.2, 12.5, 12.5, 12.2, 18.9, 17.9, 15.9,
            15.9, 15.9, 16.7, 16.7, 17.2, 16.7, 16.7, 16.4, 16.4, 16.4,
            16.4, 16.1, 16.4, 16.4, 12.7};

    double[] honda_town = {0, 8.9, 8.9, 8.3, 29.4, 29.4, 16.4, 16.4, 16.4, 13.3, 13.3,
            13.7, 13, 13.5, 13.9, 13.5, 13.7, 13.5, 11.8, 11.8, 12.7,
            13, 9, 9, 9, 9.1, 9.4, 8.8, 16.9, 15.9, 11.9,
            11.9, 11.9, 13.7, 13.7, 13.5, 13.7, 13.7, 13.9, 13.9, 13.9,
            13.9, 12.5, 13.9, 13.9, 10.2};

    double[] honda_countryside = {0, 16.9, 16.9, 16.7, 21.3, 21.3, 23.3, 23.3, 23.3, 18.9, 18.9,
            21.7, 21.3, 21.7, 22.2, 21.7, 21.7, 21.7, 20.8, 20.8, 22.2,
            22.7, 15.4, 15.4, 15.4, 15.9, 15.2, 15.9, 20, 18.9, 20,
            20, 20, 19.2, 19.2, 20.4, 19.2, 19.2, 18.5, 18.5, 18.5,
            18.5, 19.2, 18.5, 18.5, 14.5};


    String[] tata = {"รุ่น", "XENON 2.1L CNG SC CLE EIV 4X2 FLB HD", "XENON 2.2L DICOR DC DLS EIV 4X2 STY LD ABS AB", "XENON 2.2L DICOR DC DLS EIV 4X2 STY STD", "XENON 2.2L DICOR DC DLS EIV 4X4 STY LD ABS AB",
            "XENON 2.2L DICOR SC DLE EIV 4X2 CHS HD", "XENON 2.2L DICOR SC DLE EIV 4X2 FLB HD", "XENON 2.2L DICOR SC DLE EIV 4X2 FLB STD", "XENON 2.2L DICOR SC DLE EIV 4X4 FLB LD", "XENON 2.2L DICOR XC DLE EIV 4X2 FLB STD"};

    double[] tata_combined = {0, 11.6, 12.8, 12.8, 13.2, 13.2, 12.8, 12.8, 12.8, 12.8};

    double[] tata_town = {0, 8.8, 10.3, 10, 10.8, 10.8, 10.3, 10, 10.3, 10};

    double[] tata_countryside = {0, 14.1, 14.9, 15.4, 15.4, 15.4, 14.9, 15.4, 14.9, 15.4};


    String[] foton = {"รุ่น", "Tunland DC 4x2 E", "Tunland DC 4x2 S", "Tunland DC 4x2 S Premium", "Tunland DC 4x4", "Tunland SC Flat Bed", "Tunland SC Standard"};

    double[] foton_combined = {0, 13.7, 13.3, 13.3, 13.3, 13.7, 13.7};

    double[] foton_town = {0, 13.2, 13, 13, 13, 13.2, 13.2};

    double[] foton_countryside = {0, 13.9, 13.5, 13.5, 13.5, 13.9, 13.9};


    String[] mini = {"รุ่น", "Cooper Countryman", "Cooper Countryman Hightrim", "Cooper D Countryman Hightrim", "Cooper SD ALL4 Countryman Park Lane Edition"};

    double[] mini_combined = {0, 12, 12, 14.5, 14.3};

    double[] mini_town = {0, 8.8, 8.8, 11, 10.8};

    double[] mini_countryside = {0, 15.4, 15.4, 17.9, 17.9};


    String[] mercedes_benz = {"รุ่น", "C 200", "C 200 Avantgarde", "C 250 Coupe AMG Dynamic", "C 250 Coupe Sport", "C 300 BlueTEC HYBRID", "CLA 200", "CLA 250", "E 200 Sedan", "E 220 d AMG Dynamic",
            "E 220 d Avantgarde", "E 220 d Exclusive", "E 300 BlueTEC HYBRID", "GLA 200", "GLA 200 Urban", "GLA 250 AMG Dynamic", "GLC 250 d 4MATIC", "GLC 250d 4MATIC Coupe AMG Dynamic", "GLC 250d 4MATIC Coupe AMG Plus", "GLE 250 d 4MATIC",
            "ML 250 BlueTEC", "S 300 BlueTEC HYBRID L"};

    double[] mercedes_benz_combined = {0, 12.7, 16.1, 16.1, 16.4, 25, 16.4, 16.4, 12.7, 22.7,
            24.4, 22.7, 23.3, 14.3, 16.4, 15.6, 17.9, 17.9, 17.9, 14.7,
            13.3, 11.5};

    double[] mercedes_benz_town = {0, 9.7, 12.2, 12.8, 13, 23.3, 12.7, 12.3, 9.1, 20.8,
            21.7, 20.8, 23.8, 9.9, 12.7, 11.9, 15.6, 15.2, 15.2, 11.9,
            9.9, 13.3};

    double[] mercedes_benz_countryside = {0, 15.4, 19.6, 18.9, 19.2, 25, 19.6, 20.4, 16.4, 23.8,
            25.6, 23.8, 23.3, 18.9, 19.6, 18.9, 19.2, 19.6, 19.6, 16.9,
            16.4, 10.8};


    String[] mg = {"รุ่น", "MG3 HATCHBACK 1.5 D", "GS 1.5T D 2WD", "GS 1.5T X 2WD", "GS 2.0T D 2WD", "GS 2.0T X AWD", "MG3 HATCHBACK 1.5 C", "MG3 HATCHBACK 1.5 V SUNROOF", "MG3 HATCHBACK 1.5 X SUNROOF", "MG3 XROSS 1.5 X SUNROOF",
            "MG5 1.5L D", "MG5 1.5L X SUNROOF", "MG5 TURBO 1.5L D", "MG5 TURBO 1.5L X SUNROOF", "NEW MG6 FASTBACK 1.8 D SUNROOF", "NEW MG6 FASTBACK 1.8 X", "NEW MG6 FASTBACK 1.8 X SUNROOF", "NEW MG6 SEDAN 1.8 C", "NEW MG6 SEDAN 1.8 D", "NEW MG6 SEDAN 1.8 D SUNROOF",
            "NEW MG6 SEDAN 1.8 X", "NEW MG6 SEDAN 1.8 X SUNROOF", "NEW MG6 FASTBACK 1.8 C", "NEW MG6 FASTBACK 1.8 D"};

    double[] mg_combined = {0, 15.6, 15.6, 15.6, 12, 12, 15.6, 15.6, 15.6, 15.6,
            15.9, 15.9, 15.9, 15.9, 12.3, 12.3, 12.3, 12.3, 12.3, 12.3,
            12.3, 12.3, 12.3, 12.3};

    double[] mg_town = {0, 12.3, 13.7, 13.7, 9, 9, 12.3, 12.3, 12.3, 12.3,
            11.6, 11.6, 10.9, 10.9, 8.8, 8.8, 8.8, 8.8, 8.8, 8.8,
            8.8, 8.8, 8.8, 8.8};

    double[] mg_countryside = {0, 18.5, 17.2, 17.2, 15.2, 15.2, 18.5, 18.5, 18.5, 18.5,
            20.4, 20.4, 21.7, 21.7, 16.1, 16.1, 16.1, 16.1, 16.1, 16.1,
            16.1, 16.1, 16.1, 16.1};


}