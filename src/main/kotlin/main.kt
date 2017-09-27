import com.sun.deploy.util.WinRegistry

val APP_VERSION = "0.0.1.2017091520"

val MIN_KNOW_VER = 378389
val MAX_KNOW_VER = 460805

fun main(args: Array<String>) {

	println(".NET Framework Checker v.$APP_VERSION")
	println("The application can check release from $MIN_KNOW_VER to $MAX_KNOW_VER")

	// Read Windows Registry Value
	val release = WinRegistry.getInteger(
			WinRegistry.HKEY_LOCAL_MACHINE,
			"SOFTWARE\\Microsoft\\NET Framework Setup\\NDP\\v4\\Client",
			"Release"
	)

	var ver: String? = null
	var wos: String? = null
	var err : String? = null

	// Check Version
	when {
		release === null -> {
			err = "The program can not determine the version of .NET Framework on your computer. Make sure that the .NET Framework installed on your computer. The application may be out of date for checking."
		}
		release < MIN_KNOW_VER -> {
			err = "The version of .NET Framework installed on your computer is old for checking. Try googling next query: '.NET Framework version $release'";
		}
		release > MAX_KNOW_VER -> {
			err = "The version of .NET Framework installed on your computer is young for checking. Try googling next query: '.NET Framework version $release'";
		}
		release in MIN_KNOW_VER..MAX_KNOW_VER -> when(release) {
			378389 -> {
				ver = "4.5"
				wos = "All"
			}
			378675 -> {
				ver = "4.5.1"
				wos = "Windows 8.1 / Windows Server 2012 R2"
			}
			378758 -> {
				ver = "4.5.1"
				wos = "Windows 8 / Windows 7 SP1 / Windows Vista SP2"
			}
			379893 -> {
				ver = "4.5.2"
				wos = "All"
			}
			393295 -> {
				ver = "4.6"
				wos = "Windows 10"
			}
			393297 -> {
				ver = "4.6"
				wos = "Early than Windows 10"
			}
			394254 -> {
				ver = "4.6.1"
				wos = "Windows 10"
			}
			394271 -> {
				ver = "4.6.1"
				wos = "Early than Windows 10"
			}
			394802 -> {
				ver = "4.6.2"
				wos = "Windows 10 Anniversary Update"
			}
			394806 -> {
				ver = "4.6.2"
				wos = "Early than Windows 10"
			}
			460798 -> {
				ver = "4.7"
				wos = "Windows 10 Creators Update"
			}
			460805 -> {
				ver = "4.7"
				wos = "Early than Windows 10"
			}

			else -> {
				err = "The version of .NET Framework installed on your computer is between 4.5 and 4.7, but the application cannot determine it correctly. Try googling next query: '.NET Framework version $release'";
			}
		}
	}

	// Print result
	if(err != null) {
		println("Error: $err")
	}
	else {
		println("Determine release version:   $release")
		println(".NET Framework version:      $ver")
		println("OS:                          $wos")
	}

}