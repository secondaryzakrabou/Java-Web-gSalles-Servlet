import beans.Reservation;
import service.ReservationService;

public class Test {

	public static void main(String[] args) {
		ReservationService ms = new ReservationService();
		for(Reservation m : ms.findAll()) {
			System.out.println(m.getReference());
		}
	}
}
