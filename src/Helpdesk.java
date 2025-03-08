enum Category {
    SOFTWARE, HARDWARE;
}

class Employee {
    String fullName;
    int pointLevel;
    Category assignedCategory;

    public Employee(String fullName, int pointLevel, Category assignedCategory) {
        this.fullName = fullName;
        this.pointLevel = pointLevel;
        this.assignedCategory = assignedCategory;
    }
}

class Ticket {
    int id;
    String name;
    Category category;
    int point;
    String assignedEmployee = "";
    boolean isCompleted = false;

    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
    }
}

class HelpDesk {
    Employee[] employees = new Employee[2];
    Ticket[] tickets = new Ticket[5];

    public void addEmployee(Employee e, int pos) {
        if (pos >= 1 && pos <= 2) employees[pos - 1] = e;
    }

    public void addTicket(Ticket t, int pos) {
        if (pos >= 1 && pos <= 5) tickets[pos - 1] = t;
    }

    public void completeTicket(String employeeName, int ticketId) {
        for (Employee emp : employees) {
            if (emp != null && emp.fullName.equals(employeeName)) {
                for (Ticket ticket : tickets) {
                    if (ticket != null && ticket.id == ticketId && !ticket.isCompleted) {
                        if (emp.pointLevel >= ticket.point) {
                            ticket.isCompleted = true;
                            ticket.assignedEmployee = employeeName;
                            System.out.println("Ticket " + ticketId + " marked as completed by " + employeeName);
                        } else {
                            System.out.println("Ticket " + ticketId + " cannot be completed by " + employeeName + " (Insufficient points).");
                        }
                        return;
                    }
                }
            }
        }
    }

    public int getWaitingTicketCount() {
        int count = 0;
        for (Ticket ticket : tickets) if (ticket != null && !ticket.isCompleted) count++;
        return count;
    }

    public int getCompletedTicketsTotalPoint() {
        int totalPoints = 0;
        for (Ticket ticket : tickets) if (ticket != null && ticket.isCompleted) totalPoints += ticket.point;
        return totalPoints;
    }
}