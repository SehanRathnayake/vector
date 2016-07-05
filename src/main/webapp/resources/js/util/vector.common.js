/**
    * @author MaN
    *      on 7/5/2016.
    */
// Create the namespace
    
var VECTOR = VECTOR || {};
VECTOR.module = VECTOR.module || {};

VECTOR.namespace = function() {
    var a = arguments, o = null, i, j, d;
    for (i = 0; i < a.length; i = i + 1) {
        d = a[i].split(".");
        o = window.VECTOR;
        for (j = 0; j < d.length; j = j + 1) {
            o[d[j]] = o[d[j]] || {};
            o = o[d[j]];
        }
    }
    return o;
};