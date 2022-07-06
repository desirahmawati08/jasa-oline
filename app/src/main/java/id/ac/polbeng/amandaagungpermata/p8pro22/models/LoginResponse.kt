package id.ac.polbeng.amandaagungpermata.p8pro22.models

data class LoginResponse (
    val message: String,
    val error: Boolean,
    val data: User
    )