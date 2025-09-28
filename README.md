# Algorithms Assignment — MergeSort, QuickSort, DeterministicSelect, ClosestPair

## Описание
В этом проекте реализованы и протестированы 4 классических алгоритма:
1. **MergeSort** — сортировка слиянием (с cutoff и буфером).
2. **QuickSort** — робастная версия быстрой сортировки с рандомным выбором опорного элемента.
3. **DeterministicSelect** — алгоритм выбора k-го статистического порядка (median-of-medians).
4. **ClosestPair** — задача поиска ближайшей пары точек в 2D (O(n log n)).

Проект собран с использованием **Maven**, тесты написаны на **JUnit 5**.

---

## Структура проекта

algo1/
├── pom.xml
├── README.md
└── src/
├── main/java/algorithms/
│   ├── Main.java
│   ├── MergeSort.java
│   ├── QuickSort.java
│   ├── DeterministicSelect.java
│   ├── ClosestPair.java
│   └── Point.java
└── test/java/algorithms/
├── MergeSortTest.java
├── QuickSortTest.java
├── DeterministicSelectTest.java
└── ClosestPairTest.java

---

## Как запустить
1. Установить JDK (17+ или 23).
2. Установить Maven.
3. Выполнить команду для запуска тестов:
```bash
mvn test
Сборка jar: mvn package
	Запуск примеров из Main.java: java -cp target/algo1-1.0-SNAPSHOT.jar algorithms.Main
Детали реализации

MergeSort
	•	Использует общий буфер (aux) для слияний.
	•	При малом размере подмассива (≤10) переключается на сортировку вставками.
	•	Линейное слияние (две половины → один массив).
	•	Сложность: O(n log n).

QuickSort (robust)
	•	Опорный элемент выбирается случайно.
	•	Рекурсия всегда идёт в меньшую часть, большая обрабатывается итеративно.
	•	Гарантируется ограниченная глубина стека (≈ log n).
	•	Сложность: средняя O(n log n).

DeterministicSelect (Median-of-Medians)
	•	Массив разбивается на группы по 5 элементов.
	•	В каждой группе ищется медиана, из них выбирается медиана медиан.
	•	Используется для выбора pivot и последующего разбиения.
	•	Рекурсия идёт только в ту часть, где лежит k.
	•	Сложность: гарантированная O(n).

ClosestPair (2D)
	•	Массив точек сортируется по X.
	•	Деление пополам (divide & conquer).
	•	Дополнительно проверяется “полоса” шириной d вокруг середины.
	•	В полосе проверяются только ближайшие по Y соседи (~7 точек).
	•	Сложность: O(n log n).

⸻

Тестирование

В проекте есть JUnit-тесты для каждого алгоритма:
	•	MergeSortTest — проверка сортировки массивов.
	•	QuickSortTest — проверка корректности на случайных и упорядоченных массивах.
	•	DeterministicSelectTest — проверка поиска k-го элемента.
	•	ClosestPairTest — проверка вычисления минимального расстояния между точками.

Все тесты должны проходить успешно (mvn test).

⸻

Чек-лист
	•	Реализован MergeSort с cutoff и буфером.
	•	Реализован QuickSort с random pivot и хвостовой оптимизацией.
	•	Реализован DeterministicSelect (Median-of-Medians).
	•	Реализован ClosestPair через D&C и проверку “полосы”.
	•	Написаны JUnit-тесты.
	•	Есть README.
