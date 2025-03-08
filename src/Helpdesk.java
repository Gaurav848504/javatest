enum Category {
    SOFTWARE, HARDWARE;
}

class Employee {
    private String fullName;
    private int pointLevel;
    private Category assignedCategory;

    public Employee(String fullName, int pointLevel, Category assignedCategory) {
        this.fullName = fullName;
        this.pointLevel = pointLevel;
        this.assignedCategory = assignedCategory;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPointLevel() {
        return pointLevel;
    }

    public boolean canResolve(Ticket ticket) {
        return this.pointLevel >= ticket.getPoint();
    }
}

class Ticket {
    private int id;
    private String name;
    private Category category;
    private int point;
    private String assignedEmployee = "";
    private boolean isCompleted = false;

    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPoint() {
        return point;
    }

    public void markCompleted(Employee employee) {
        if (employee.canResolve(this)) {
            this.isCompleted = true;
            this.assignedEmployee = employee.getFullName();
            System.out.println("Ticket " + id + " marked as completed by " + assignedEmployee);
        } else {
            System.out.println("Ticket " + id + " cannot be completed by " + employee.getFullName() + " (Insufficient points).");
        }
    }
}

class HelpDesk {
    private Employee[] employees = new Employee[2];
    private Ticket[] tickets = new Ticket[5];

    public void addEmployee(Employee e, int pos) {
        if (pos >= 1 && pos <= 2) employees[pos - 1] = e;
    }

    public void addTicket(Ticket t, int pos) {
        if (pos >= 1 && pos <= 5) tickets[pos - 1] = t;
    }

    public void completeTicket(String employeeName, int ticketId) {
        Employee employee = findEmployeeByName(employeeName);
        Ticket ticket = findTicketById(ticketId);

        if (employee != null && ticket != null && !ticket.isCompleted()) {
            ticket.markCompleted(employee);
        }
    }

    private Employee findEmployeeByName(String name) {
        for (Employee emp : employees) {
            if (emp != null && emp.getFullName().equals(name)) return emp;
        }
        return null;
    }

    private Ticket findTicketById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getId() == id) return ticket;
        }
        return null;
    }

    public int getWaitingTicketCount() {
        int count = 0;
        for (Ticket ticket : tickets) if (ticket != null && !ticket.isCompleted()) count++;
        return count;
    }

    public int getCompletedTicketsTotalPoint() {
        int totalPoints = 0;
        for (Ticket ticket : tickets) if (ticket != null && ticket.isCompleted()) totalPoints += ticket.getPoint();
        return totalPoints;
    }
}
