class Main {
        public static void main(String[] args) {
                Employee e1 = new Manager("Anna", 1200);
                Employee e2 = new Subordinate("Bea", 1000);
                System.out.println(e2.getName());
                System.out.print(e2.getSalary());
                System.out.print(" -> ");
                e2.raiseSalary(20);
                System.out.println(e2.getSalary());
                System.out.println(e1.getName());
                System.out.print(e1.getSalary());
                System.out.print(" -> ");
                e1.raiseSalary(30);
                System.out.println(e1.getSalary());
        }
}