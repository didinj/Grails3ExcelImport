package grailsimportexcel

class Subscriber {

    String email
    String fullname

    static constraints = {
      email email:true
      fullname blank: false, size: 0..100
    }
}
