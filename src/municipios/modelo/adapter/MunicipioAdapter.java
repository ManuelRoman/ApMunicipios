package municipios.modelo.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import municipios.modelo.entity.Municipio;

public class MunicipioAdapter implements JsonSerializer<Municipio> {

	@Override
	public JsonElement serialize(Municipio municipio, Type type, JsonSerializationContext jsc) {
		JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idMunicipio", municipio.getIdMunicipio());
        jsonObject.addProperty("nombre", municipio.getNombre());
        jsonObject.addProperty("codMunicipio", municipio.getCodMunicipio());
        jsonObject.addProperty("dc", municipio.getDc());
        return jsonObject;
	}

}
