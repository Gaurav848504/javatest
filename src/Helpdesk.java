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
    String assignedEmployee="";
    boolean isCompleted=false;

    public Ticket(int id, String name, Category category, int point) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.point = point;
    }
}

class HelpDesk {
    Employee emp1, emp2;
    Ticket[] tickets = new Ticket[5];

    void addEmployee(Employee e, int pos) {
        if (pos == 1) {
            emp1 = e;
        }
        else{
            emp2 = e;
        }

    }

    void addTicket(Ticket t, int pos) {
        if (pos >= 1 && pos <= 5) {
            tickets[pos - 1] = t;
        }
    }

    void completeTicket(String employeeName, int ticketId) {
        Employee emp = null;

        if (emp1 != null && emp1.fullName.equals(employeeName)) {
            emp = emp1;
        } else if (emp2 != null && emp2.fullName.equals(employeeName)) {
            emp = emp2;
        }


        if (emp == null) return;

        for (Ticket t : tickets) {
            if (t != null && t.id == ticketId && !t.isCompleted) {
                if (emp.pointLevel >= t.point) {
                    t.isCompleted = true;
                    t.assignedEmployee = emp.fullName;
                    System.out.println("Ticket " + t.id + " marked as completed by " + emp.fullName + ".");
                } else {
                    System.out.println("Ticket " + t.id + " cannot be completed by " + emp.fullName + " (Insufficient points).");
                }
                return;
            }
        }
    }

    int getWaitingTicketCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (t != null && !t.isCompleted)
            {
                count++;
            }
        }
        return count;
    }

    int getCompletedTicketsTotalPoint() {
        int sum = 0;
        for (Ticket t : tickets) {
            if (t != null && t.isCompleted) sum += t.point;
        }
        return sum;
    }
}