import org.scalatest.{FlatSpec, Matchers}

class MainTest extends FlatSpec with Matchers {


  val expectedEncryptedValue = "B6rwVUwePxfVeY+sDDhDaYX/KgVs3Pwf3c3f/gYxrgZ1GTyJEBcQ2jk6oJIEtz1ef8By4VCjSThxo4P2Qu8j9l8iAi+xvG27tiG0yfn2XHhz5Sn9g4ye4HIGQ96ojDu5c74oyPC1+/avjp8Kaz/4go+BArxv5fV1JPm+1kqZyTAL5+iLEsM/7eleX1da5PcppVUiq/y3rGD73L+t634Ut2xURgb2NBdgJcTQabW+ucLbyBTWoZzQPcht9XImsvY6LluoSuiJaYn7I3L1H10GRJ3jHB8mrezbXsHsDkHIVIbEmBD7eu6NVybSkWJQ+YxSJTN9qLVhvUz86ur1mKe8e5XkQMI/o2OD9rg69E4MbTPMwU7HQ5aR8zTBed9dO9R7/8++WX5rsdAHs3AuoufGaoNjAu7B1xHj51ok70lg0x7WrEH/ujoiKxbD8u7T/BtZY4BAQMK9PiaN0pMyQ6GNepZuS5b0TuMW+kI9AcbBTtZT6wvW4pWU1vgnVsyPcacu34MlSlygvsPTVe+E0P4S5JLAl6nCD8Kj15gCrgH1FTrr70CReoRUZ2/iyQpgqhfESdNbkX0Vh/1q4SOYm5dtlfO9wU5S3bV/PEHRU7Uok4JZy3QweK91Do14LsuTT9Nqmf8tVLcsHB4hvAh28BkcsT/39Bv/FhP8oWRiu7mgRA8="
  val rowValue = "welcome mahmoud it works perfectly"
  val main:Main = new Main
  "A row value after encryption " should "match expected one" in {

    val realEncryptedValue= main.encryptText(rowValue)

    expectedEncryptedValue shouldEqual realEncryptedValue

  }

 "expected encrypted value" should "match the row value after decrypting" in  {

   val realDecryptedValue:String = main.decryptText(expectedEncryptedValue)
   realDecryptedValue shouldEqual realDecryptedValue
 }
}
