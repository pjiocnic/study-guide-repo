<h1>Structural Pattern - COR</h1>

# Example 1


1. PRManager

```java
public class PRManager {
  private BranchManager branchManager;
  private RegionalDirector regionalDirector;
  private VicePresident vicePresident;
  private PresidentCOO coo;
  public static void main(String[] args) {
    PRManager manager = new PRManager();
    manager.createAuthorizationFlow();
    PurchaseRequest request =
     new PurchaseRequest(1, "Office Supplies”,10000);
    manager.branchManager.authorize(request);
    request = new PurchaseRequest(2, "HardWare Procurement”,
              175000);
    manager.branchManager.authorize(request);
    request = new PurchaseRequest(3, "AD Campaign”,800000);
    manager.branchManager.authorize(request);
  }
  public void createAuthorizationFlow() {
    branchManager = new BranchManager("Robin");
    regionalDirector = new RegionalDirector("Oscar");
    vicePresident = new VicePresident("Kate");
    coo = new PresidentCOO("Drew");
    branchManager.setNextHandler(regionalDirector);
    regionalDirector.setNextHandler(vicePresident);
    vicePresident.setNextHandler(coo);
  }
}//End of class
```

2. Abstract PRHandler

```java
public abstract class PRHandler {
  private PRHandler nextHandler;
  private String handlerName;
  public PRHandler(String name) {
    handlerName = name;
  }
  public String getName() {
    return handlerName;
  }
  public abstract boolean authorize(PurchaseRequest request);
  public PRHandler getNextHandler() {
    return nextHandler;
  }
  public void setNextHandler(PRHandler handler) {
    nextHandler = handler;
  };
}
```

3. Implementation classes

```java
class BranchManager extends PRHandler {
     static double LIMIT = 25000;
     public BranchManager(String name) {
       super(name);
     }
     public boolean authorize(PurchaseRequest request) {
       double amount = request.getAmount();
       if (amount <= LIMIT) {
          System.out.println(" Branch Manager " + getName() +
                             " has authorized the PR - " + request);
          return true;
       } else {
         //forward the request to the next handler
         return getNextHandler().authorize(request);
       }
  }
}//End of class
class RegionalDirector extends PRHandler {
  static double LIMIT = 100000;
  public RegionalDirector(String name) {
    super(name);
  }
  public boolean authorize(PurchaseRequest request) {
    double amount = request.getAmount();
    if (amount <= LIMIT) {
      System.out.println(" Regional Director " + getName() +
                         " has authorized the PR - " +
                         request);
      return true;
    } else {
     //forward the request to the next handler
     return getNextHandler().authorize(request);
    }
 }
}//End of class
class VicePresident extends PRHandler {
  static double LIMIT = 200000;
  public VicePresident(String name) {
    super(name);
  }
  public boolean authorize(PurchaseRequest request) {
    double amount = request.getAmount();
    if (amount <= LIMIT) {
      System.out.println(" V.P. " + getName() +
                         " has authorized the PR - " + request);
      return true;
    } else {
     //forward the request to the next handler
      return getNextHandler().authorize(request);
    }
  }
}//End of class
class PresidentCOO extends PRHandler {
   static double LIMIT = 400000;
   public PresidentCOO(String name) {
     super(name);
   }
   public boolean authorize(PurchaseRequest request) {
     double amount = request.getAmount();
    if (amount <= LIMIT) {
      System.out.println(" President & COO " + getName() +
                         " has authorized the PR - " + request);
      return true;
    } else {
      System.out.println("PR - " + request +
                         " couldn't be authorized.\n " +
                         "Executive Board needs to be " +
                         "consulted for approval \n" +
                         "reason: Amount too large");
      return false;
    }
 }
}//End of class
```

4. PurchaseRequest

```java
class PurchaseRequest {
  private int ID;
  private String description;
  private double amount;
  public PurchaseRequest(int id, String desc, double amt) {
    ID = id;
    description = desc;
    amount = amt;
  }
  public double getAmount() {
    return amount;
  }
  public String toString() {
    return ID + ":" + description;
  }
}
```

# Example 2: COR using Lambdas

**Ref:** Modern Java in Action Mario Fusco, Raoul-Gabriel Urma, Alan Mycroft

1.

```java
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
    }
    public T handle(T input) {
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleWork(T input);
}
```

2. The Handlers

```java
public class HeaderTextProcessing extends ProcessingObject<String> {
    public String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
public class SpellCheckerProcessing extends ProcessingObject<String> {
    public String handleWork(String text) {
        return text.replaceAll("labda", "lambda");       
    }
}
```

3. Driver

```java
ProcessingObject<String> p1 = new HeaderTextProcessing();
ProcessingObject<String> p2 = new SpellCheckerProcessing();
p1.setSuccessor(p2);                                              1
String result = p1.handle("Aren't labdas really sexy?!!");
System.out.println(result);
```

## Using Lambdas

```java

UnaryOperator<String> headerProcessing =
    (String text) -> "From Raoul, Mario and Alan: " + text;       // The first processing object
UnaryOperator<String> spellCheckerProcessing =
    (String text) -> text.replaceAll("labda", "lambda");          // The second processing object

Function<String, String> pipeline =
    headerProcessing.andThen(spellCheckerProcessing);   // Compose the two functions, resulting in a chain of operations.
String result = pipeline.apply("Aren't labdas really sexy?!!");
```