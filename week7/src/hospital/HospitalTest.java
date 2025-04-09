package hospital;

//Driver class
public class HospitalTest {
 public static void main(String[] args) {
     Doctor doc = new Doctor(1, "Dr. Dibas");
     Nurse nurse = new Nurse(2, "bibas");
     Receptionist receptionist = new Receptionist(3, "digant");
     Cleaner cleaner = new Cleaner(4, "ram");

     doc.diagnose();
     nurse.assist();
     receptionist.answerCalls();
     cleaner.clean();
 }
}


