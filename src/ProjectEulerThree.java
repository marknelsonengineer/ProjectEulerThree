/**
 * Created by Mark Nelson on 2/2/15.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */
public class ProjectEulerThree {

  // Global Constants
  //static final long lInitialValue = 13195L;
  static final long lInitialValue = 600851475143L;


  // This could be done without evil-globals, but I'm banging this out quickly.
  static long lgCurrent = -1;
  static long lgMid = -1;

  private static void setCurrentValue( long lCurrent ) {
    lgCurrent = lCurrent;
    lgMid = lCurrent/2 + 1;

    System.out.printf("The current number is: %d  The current midpoint is: %d\n", lgCurrent, lgMid);
  }

  private static boolean isPrime (long lCandidate) {
    if( lCandidate == 2) return true;
    if( lCandidate%2 == 0 ) return false;

    long lMid = lCandidate/2 + 3;

    for( int j = 3 ; j < lMid ; j+= 2 ) {
      // System.out.printf( "Trying %d.  Mid %d\n", j, lMid);
      if( lCandidate%j == 0) return false;
    }

    return true;
  }


  private static long getNextPrime( long lPrime, long lMid  ) {
    if( lPrime < 2 )
      System.exit( 1 );  // Panic and bail.

    if( lPrime == 2 )    // 2 is a unique prime...  The next one is 3.
      return 3l;

    for( long i = lPrime + 2 ; i <= lMid ; i+=2 )  // Iterate, skipping by 2 looking for primes.  Quit if we reach mid.
      if( isPrime( i ) )
        return i;

    System.out.printf( "Done.  Current number is: %d (which should also be prime).\n", lgCurrent );
    System.exit( 0 );  // Normal exit condition.  No more primes.
    return 0;
  }


  public static void main(String[] args) {

    long lPrime = 2;
    int iWatchdog = 1000 ;  // Decrement and quit if it reaches 0.  Prevent runaway loops.

    System.out.printf( "Starting ProjectEulerThree\n");

    setCurrentValue( lInitialValue );

    // This is a tricky loop to understand... pay close attention
    // Iterate through prime numbers...
    // If the current prime goes beyond the midpoint of the current number, then there are no more multiples.
    // (Note:  There is probably a much better way as we've already tried multiples > 2)... but this is good enough for now
    // If I find a multiple, then print it, set a new midpoint and ** Continue ** the while loop with the current prime.  This way we can
    // find multiple primes, such as 2 * 3 * 3 * 5.
    //
    while( lPrime <= lgMid && iWatchdog > 0 ) {
      if( lgCurrent % lPrime == 0 ) {
        System.out.printf( "Found a prime multiple:  %d\n", lPrime);

        setCurrentValue( lgCurrent / lPrime );
        continue;
      }

      lPrime = getNextPrime( lPrime, lgMid );

      iWatchdog--;
    }

    System.out.printf("Abnormal exit\n");
    // The normal exit condition is at the end of getNextPrime().
  }

}
