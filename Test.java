package newIo;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Entity employee = new Entity("employee", "name", "salary", "year", "month", "day");
		Entity[] employees = new Entity[3];
//		for (Entity entity : employees) {
//			//foreach has granted the value to the item in an array, which won't impact the real item in array
//			entity = employee.clone();
//		}
		for (int i = 0; i < employees.length; i++) {
			employees[i] = employee.clone();
		}
		
		employees[0].setValues("Carl Cracker", 75000, 1987, 12, 15);
		employees[1].setValues("Harry Hacker", 55000, 1997, 2, 12);
		employees[2].setValues("Tony Tester", 45000, 1993, 8, 25);
		for (Entity entity : employees) {
			System.out.println(entity);
		}
		
		Entity e = new Entity("student", null);
		Entity f = new Entity("student", "yes");
		Entity g = new Entity("student", "yes", null);
		
		System.out.println(e + "\n"+f + "\n"+ g + "\n" + "yes".contains(""));
		
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(System.getProperty("line.separator") + "...");
	}

}
