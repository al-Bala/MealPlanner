db.createCollection("products")

db.products.insertMany([
    {
        _id: ObjectId('658c088e98487458f640453b'),
        name: "kasza jaglana",
        packing_units: ["g","kg"],
        main_unit: "g",
        packing_measures: [400,1000]
    },
    {
        _id: ObjectId('658c088e98487458f640453c'),
        name: "marchew",
        packing_units: ["g","kg","szt"],
        main_unit: "g",
        packing_measures: [100]
    },
    {
        _id: ObjectId('658c088e98487458f640453d'),
        name: "brokuł",
        packing_units: ["g","kg","szt"],
        main_unit: "g",
        packing_measures: [150]
    },
    {
        _id: ObjectId('658c088e98487458f640453e'),
        name: "oliwa z oliwek",
        packing_units: ["ml","l"],
        main_unit: "ml",
        packing_measures: [50,250,1000]
    },
    {
        _id: ObjectId('659d8d69d6d8ec0007e14d76'),
        name: "jajko",
        packing_units: ["szt"],
        main_unit: "szt",
        packing_measures: [6,10]
    },
    {
        _id: ObjectId('65a43bcc6b150200075ba406'),
        name: "ryż",
        packing_units: ["g", "kg"],
        main_unit: "g",
        packing_measures: [400, 1000]
    },
    {
        _id: ObjectId('65a43be46b150200075ba407'),
        name: "kurczak",
        packing_units: ["g", "kg"],
        main_unit: "g",
        packing_measures: [400, 600]
    },
    {
        _id: ObjectId('65a43bfc6b150200075ba408'),
        name: "sos sojowy",
        packing_units: ["ml", "l"],
        main_unit: "ml",
        packing_measures: [150]
    },
    {
        _id: ObjectId('65a980d8d5a2820007ae5a42'),
        name: "papryka",
        packing_units: ["ml", "l"],
        main_unit: "ml",
        packing_measures: [150]
    },
    {
        _id: ObjectId('65a98106d5a2820007ae5a43'),
        name: "pomidor",
        packing_units: ["ml", "l"],
        main_unit: "ml",
        packing_measures: [150]
    },
    {
        _id: ObjectId('65a9813ad5a2820007ae5a44'),
        name: "cebula",
        packing_units: ["ml", "l"],
        main_unit: "ml",
        packing_measures: [150]
    },
]);

