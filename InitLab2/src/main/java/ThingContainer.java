/**
 * Created by simonhamermesh on 1/21/16.
 */
import com.sun.tools.javac.util.ArrayUtils;
import java.util.Random;

public class ThingContainer {

    Random rand = new Random();
    String name;
    ColorfulThing[] ctArray;
    ColorfulThing[] ctArrayTRANSFER;
    ColorfulThing popped;
    ColorfulThing removed;


    //Constructor with no args, ctArray size set manually.
    ThingContainer (){
        ctArray = new ColorfulThing[1];
    }

    //ThingContainer Constructor - Takes an int to initialize size of its ctArray.
    ThingContainer(int arraySize, String tcName){
        name = tcName;
        ctArray = new ColorfulThing[arraySize];
        add();
    }

    ThingContainer(ColorfulThing[] inputCTArray, String tcName ){
        name = tcName;
        ctArray = inputCTArray;
    }

    /*Will randomly add up to 10 ColorfulThing objects to it ctArray. If number of objects
    added exceeds container size, will break addition loop.*/
    private void add(){
        for(int i = 0;i<ctArray.length+rand.nextInt(2);i++) {

            if(i > (ctArray.length-1)){
                System.out.println("Thing Container Is Full");
                break;
            }

            ctArray[i] = new ColorfulThing(ColorfulThing.getRandomColor());
        }
    }

     void printThings(){
        System.out.println("ThingContainer Name: " + name);
         for(ColorfulThing  x : ctArray ){
            if(x != null) {
                System.out.println(x.getEnumColor());
            }
        }
        //System.out.println();
     }

    ColorfulThing pop(){
        popped = ctArray[ctArray.length-1];
        ctArrayTRANSFER  = new ColorfulThing[ctArray.length-1];
        for(int i = 0; i<ctArray.length-1; i++ ){
            ctArrayTRANSFER[i]=ctArray[i];
        }
        ctArray = ctArrayTRANSFER;

        /*System.arraycopy(ctArray,0,ctArray,0,(ctArray.length-1));****/

        System.out.println();
        System.out.println("You have chosen to pop. The new array is: ");
        printThings();
        return popped ;
    }

    ColorfulThing remove (Color input){

        boolean present = false;
        boolean found = false;
        System.out.println("You have chosen to remove the first instance of: " + input.name());

        /*******CHECK FOR PRESENCE - .contains() analog********/
        for(int i = 0; i<ctArray.length;i++){
            if(!(ctArray[i].enumColor == input)){
                present = false;
                continue;
            }
            present = true;
            break;
        }
        if(!present){return null;}
        //org.apache.commons.lang3.ArrayUtils.contains(ctArray,ColorfulThing.input);
        /*********END OF .contains() analog***********/


        ctArrayTRANSFER  = new ColorfulThing[ctArray.length-1];

        for(int i = 0; i<ctArray.length;i++) {

            if (!found && (ctArray[i].enumColor == input)) {
                removed = ctArray[i];
                found = true;
                continue;
            }
            if(!found){ctArrayTRANSFER[i] = ctArray[i];}
             else {ctArrayTRANSFER[i-1] = ctArray[i];}
        }

        ctArray = ctArrayTRANSFER;

        /*org.apache.commons.lang3.ArrayUtils.remove(ctArray,i);
        This did not work. Not sure why. Should have removed item i from ctArray*/


        System.out.println("The new array is:");
        printThings();
        return removed;

        }

    ColorfulThing remove (ColorfulThing input){
        if(remove(input.enumColor)!=null){
        return removed;}
        return null;
    }

    private static  ColorfulThing[] createInputCTArray(ColorfulThing[] input){
        for(int i = 0; i<input.length; i++){
            input[i] = new ColorfulThing(ColorfulThing.getRandomColor());
        }

        System.out.println("Made a Custom Initialization Array");
        for(ColorfulThing  x : input  ){
            if(x != null) {
                System.out.println(x.getEnumColor());
            }
        }
            return input;
    }

    //*********************BEGINNING OF MAIN********************//
    public static void main (String[] args) {

        Random rand = new Random();

        //ColorfulThing ct1 = new ColorfulThing(ColorfulThing.getRandomColor());
        //System.out.println(ct1.getEnumColor()); //THIS WORKS

        //ThingContainer tc = new ThingContainer();
        //tc.ctArray[0]= new ColorfulThing(ColorfulThing.getRandomColor());
        //System.out.println(tc.ctArray[0].getEnumColor()); //THIS WORKS



        /*************** INITLAB2 PART1 - Populate Thing Container, Print contents. ********************************/
        ThingContainer tc1 = new ThingContainer((rand.nextInt(5)+2), "tc1");
        System.out.println("Your initialized ThingContainer is:");
        tc1.printThings();



        /************** INITLAB2 PART 2.1 - Pop the last item out of container, return it, print new container*****/
        System.out.println("This item has been popped: "+ tc1.pop().getEnumColor());
        System.out.println();


        /************** INITLAB2 PART2.2 - Specify a Color, remove the first ColorfulThing with that Color ********/
        try{
        System.out.println("This item has been removed: " + tc1.remove(ColorfulThing.getRandomColor()).getEnumColor());}
        catch(NullPointerException e){
            System.err.println("The item you wish to remove is not present");
            tc1.printThings();
        }
        System.out.println();

        /************** INITLAB2 PART2.3 - Specify a ColorfulThing, remove the first ColorfulThing with that Color ********/
        ColorfulThing toRemove = new ColorfulThing(ColorfulThing.getRandomColor());
        try{
            System.out.println("This item has been removed: " + tc1.remove(toRemove).getEnumColor());}
        catch(NullPointerException e){
            System.err.println("The item you wish to remove is not present");
            tc1.printThings();
        }
        System.out.println();


        /************** INITLAB2 PART 3 - Initialize the ThingContainer's ColorfulThing Array with predetermined Array ****/
        ColorfulThing[] inputCTArray = new ColorfulThing[(rand.nextInt(5)+2)];
        inputCTArray = createInputCTArray(inputCTArray);

        ThingContainer inputInitializedTC = new ThingContainer(inputCTArray, "Custom Initialized");
        inputInitializedTC.printThings();




       /* ThingContainer tc2 = new ThingContainer((rand.nextInt(5)+2), "tc2");
        tc2.printThings();
        System.out.println("This item has been popped: "+ tc2.pop().getEnumColor());
        System.out.println();

        ThingContainer tc3 = new ThingContainer((rand.nextInt(5)+2), "tc3");
        tc3.printThings();
        System.out.println("This item has been popped: "+ tc3.pop().getEnumColor());
        System.out.println();
        */
    }
    //************************END OF MAIN*************************//

}


