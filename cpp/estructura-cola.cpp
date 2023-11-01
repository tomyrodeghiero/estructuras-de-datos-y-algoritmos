#include <iostream>
#include <list>
#include <stdexcept> // para std:runtime_error
#include <string>
#include <sstream>

template <typename T>
class Cola {
private:
    std::list<T> lista;

public:
    // Retorna si la cola no tiene elementos
    bool esVacia() const {
        return lista.empty();
    }

    // Encola un elemento en el comienzo de la cola
    bool encolar(const T& elemento) {
        try {
            lista.push_back(elemento);
            return true;
        } catch (std::bad_alloc& e) {
            // throw std::runtime_error("No hay memoria disponible");
            return false;
        }
    }

    // Desencola el primer elemento de la cola
    T desencolar() {
        if (esVacia()) {
            throw std::runtime_error("La cola está vacía");
        }

        T elemento = lista.front();
        lista.pop_front();
        return elemento;
    }

    // Retorna el primer elemento de la cola
    T primero() const {
        if (esVacia()) {
            throw std::runtime_error("La cola está vacía");
        }

        return lista.front();
    }

    // Remueve todos los elementos de la cola
    void vaciar() {
        lista.clear();
    }

    // Retorna la cantidad de elementos de la cola
    int elementos() const {
        return lista.size();
    }

    // Invariante de la clase
    bool rep_ok() const {
        return true;
    }

    // Retorna una representación en string de la cola
    std::string to_str() const {
        std::stringstream ss;
        ss << "[";
        for (auto it = lista.begin(); it != lista.end(); ++it) {
            ss << *it;
            if (std::next(it) != lista.end()) {
                ss << ", ";
            }
        }
        ss << "]";
        return ss.str();
    }

    // Evalúa si dos colas son iguales
    bool equals(const Cola<T>& otra) const {
        return lista == otra.lista;
    }
};

int main() {
    Cola<int> cola;
    cola.encolar(1);
    cola.encolar(2);
    cola.encolar(3);
    cola.encolar(4);
    cola.encolar(5);

    std::cout << cola.primero() << std::endl; // 1
    std::cout << cola.desencolar() << std::endl; // 1
    std::cout << cola.primero() << std::endl; // 2

    return 0;
}