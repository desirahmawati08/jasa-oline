package id.ac.polbeng.amandaagungpermata.p8pro22.models

data class JasaResponse (
    val message: String,
    val error: Boolean,
    val datas: List<Jasa>
    )