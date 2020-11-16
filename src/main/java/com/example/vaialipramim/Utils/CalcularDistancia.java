package com.example.vaialipramim.Utils;

public class CalcularDistancia {

    private static double degrausParaRadianos(double degraus){
        return degraus * (Math.PI / 180);
    }

    public  static double distanciaEmKMEntreCoordenadas(Coordenadas coordenadasSolicitante, Coordenadas coordenadasEntregador){

        int RADIUS = 6371;

        double latitudeCoordenadasSolicitante = coordenadasSolicitante.getLatitude();
        double longitudeCoordenadasSolicitante = coordenadasSolicitante.getLongitude();

        double latitudeCoordenadasEntregador = coordenadasEntregador.getLatitude();
        double longitudeCoordenadasEntregador = coordenadasEntregador.getLongitude();

        double deltaLatitude = degrausParaRadianos(latitudeCoordenadasEntregador - latitudeCoordenadasSolicitante);
        double deltaLongitude= degrausParaRadianos(longitudeCoordenadasEntregador - longitudeCoordenadasSolicitante);

        double arco = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)+
                Math.cos(degrausParaRadianos(latitudeCoordenadasEntregador))*
                        Math.cos(degrausParaRadianos(latitudeCoordenadasEntregador))*
                        Math.sin(deltaLongitude/ 2) *
                        Math.sin(deltaLongitude/ 2);

        double centro = 2 * Math.atan2(Math.sqrt(arco), Math.sqrt(1 - arco));

        double distancia = RADIUS * centro;
        return distancia;

    }


}
