package com.cibertec.examen2.servicio;

        import com.cibertec.examen2.entidad.TipoReclamo;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.DELETE;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.PUT;
        import retrofit2.http.Path;

public interface ServicioRest {

    //Crud de Rol
    @GET("tiporeclamo")
    public abstract Call<List<TipoReclamo>> listaTipoReclamo();

    @POST("tiporeclamo")
    public abstract Call<TipoReclamo> agregaTipoReclamo(@Body TipoReclamo tiporeclamo);

    @PUT("tiporeclamo")
    public abstract Call<TipoReclamo> actualizaTipoReclamo(@Body TipoReclamo tiporeclamo);

    @DELETE("tiporeclamo/{idTipoReclamo}")
    public abstract Call<TipoReclamo> eliminaTipoReclamo(@Path("idTipoReclamo") int id);

}
