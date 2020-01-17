// Create Patient
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///PATIENT.csv" AS row
CREATE (:Patient {idPatient: row.PatientID, numPatient: row.NumPatient, sexe: row.Sexe, dateNaissance: date(row.DateNaissance), prenom: row.Prenom, nom: row.Nom});


// Create CIMO3Morpho
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///CIMO3_MORPHO.csv" AS row
CREATE (:CIMO3Morpho {codeMorphoCIMO3: row.CodeMorphoCIMO3, libelleCodeCIMO3: row.LibelleCodeCIMO3, codeGroupeMorpho: row.CodeGroupeMorpho});


// Create CIMO3Topo
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///CIMO3_TOPO.csv" AS row
CREATE (:CIMO3Topo {codeTopoCIMO3: row.CodeTopoCIMO3, libelleTopoCIMO3: row.LibelleTopoCIMO3, codeGroupeTopo: row.CodeGroupeTopo});

// Create GroupeTopo
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///GROUPE_TOPO.csv" AS row
CREATE (:GroupeTopo {codeTopoCIMO3: row.CodeTopoCIMO3, libelleGroupeTopo: row.LibelleGroupeTopo, codeGroupeTopo: row.CodeGroupeTopo});

// Create GroupeMorpho
USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///GROUPE_MORPHO.csv" AS row
CREATE (:GroupeMorpho {codeMorphoCIMO3: row.CodeMorphoCIMO3, libelleGroupeMorpho: row.LibelleGroupeMorpho, codeGroupeMorpho: row.CodeGroupeMorpho});


// Create GroupeMorpho
//using merge to verify if data already exists
LOAD CSV WITH HEADERS FROM "file:///GROUPE_MORPHO.csv" AS row
MERGE (c:GroupeMorpho {codeGroupeMorpho : row.CodeGroupeMorpho})
SET c.codeMorphoCIMO3 = row.CodeMorphoCIMO3, c.libelleGroupeMorpho = row.LibelleGroupeMorpho, c.codeGroupeMorpho = row.CodeGroupeMorpho
RETURN count(c)



// create relationship by id

match (c:CIMO3Topo) match(g:GroupeTopo) where c.codeGroupeTopo in g.codeGroupeTopo merge(g)<-[:appartient]->(c)

// compter le nombre de code appartenant au groupe 7
match (g:GroupeMorpho{codeGroupeMorpho:"7"}) with g match(g)<-[r:appartient]- (c:CIMO3Morpho) return g,count(c)


// map to RDF
:POST /rdf/cypher
{ "cypher" : "MATCH path = (n:Order { orderID : '10785'})-[:ORDERS]->()-[:PART_OF]->(:Category { categoryName : 'Beverages'}) RETURN path " , "format": "RDF/XML" , "mappedElemsOnly" : true }