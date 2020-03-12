/****************************************************************************
* Program Name   : DreamHome class
* Group          : Smart House
* Date           : 11/27/18
* Course/Section : CSC 490
* Program Description: Starts the program.
*
* Methods+-:
* +void main - Starts the program.
****************************************************************************/
public class DreamHome {
        
        public static void main(String [] args) throws Exception {
                
                //local constants
                final int sec = 1000;    //Thread.sleep number for one second
                
                //local variables
                Sensors channels = new Sensors();    //Sensor class to set up and control the channels
                
                /********** start main **********/
                
                //Set up the channels**
                channels.setUpChannels();
                
                //Do stuff
                for(int i = 0; i < 30; i++) {
                        
                        //Get data from sensors**
                    channels.getData(sec);
                
                    //This conditional is called when someone takes a shower and the humidity in the bathroom goes up
                    if(channels.humidityFlag) {
                        channels.humidityFlag = false;
                        System.out.println("\nFLAG SUCCESSFULLY READ!!!\n FAN ON");
                        channels.getOutputCH("CH4").setDutyCycle(1.0); // this call turns the fan on            
                    }
                    else {
                        channels.getOutputCH("CH4").setDutyCycle(0.0);
                    } //End if
                        
                        //This conditional is called when someone uses the correct RFID flag
                        if(channels.rfidPositiveFlag) {
                                channels.rfidPositiveFlag = false;
                                System.out.println("\nFlag SUCCESFULLY READ!!!\n Let the person inside");
                        
                                //Turns on the interface kits green light to indicate that someone's ID worked
                                channels.getOutputCH("CH1").setDutyCycle(1.0);
                            Thread.sleep(810);
                            channels.getOutputCH("CH1").setDutyCycle(0.0);
                            
                            //Turns the living room light on
                            channels.getOutputCH("CH2").setDutyCycle(1.0);
                            
                        } //End if
                        
                        //This conditional is called when someone uses the wrong RFID card
                        if(channels.rfidNegativeFlag) {
                                channels.rfidNegativeFlag = false;
                                System.out.println("\nFlag SUCCESFULLY READ!!!\n keep the person outside");
                            
                                //Turns on the interface kits red light to indicate that someone's ID did not work
                                channels.getOutputCH("CH0").setDutyCycle(1.0);
                            Thread.sleep(810);
                            channels.getOutputCH("CH0").setDutyCycle(0.0);
                            
                        } //End if
                
                } //End for
                
                //Close all channels**
                channels.closeChannels();
                
        } //End main method
        
} //End DreamHome class
