/*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getNumberOfProductPerOrder.sql
 * Ficheiro:  getNumberOfProductPerOrder.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getNumberOfProductPerOrder.sql
 */

create or replace function getTotalPayloadByStock(p_barCode Product.barCode%TYPE, p_pharmacyId Pharmacy.id%Type) return float
  is
    v_WeightNeeded float;
BEGIN
     select weight into v_WeightNeeded
     from Product
     where barcode = p_barCode AND Pharmacyid = p_pharmacyId;

     return v_WeightNeeded;

END;
/
commit;