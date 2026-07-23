package vunge.ao.meal.util;

public interface Execute<RETURN, REQUEST> {
    RETURN execute (REQUEST request);
}