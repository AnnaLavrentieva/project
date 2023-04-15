package com.lavrentieva.repository;



public class Examples {



//    List<Tutorial> findByLevelAndPublished(int level, boolean isPublished);

//для пошуку
//List<Tutorial> findByTitleContainingIgnoreCase(String title);
//Containing: where x.field like %param% (wrapped in %)

//    Asc (default) for date - from old to new
//List<Tutorial> findByOrderByLevel();
//OrderBy keyword along with Asc (default) and Desc for sorting by field/column

//    sort more than one field, you need to pass Sort object as parameter with the help of Order class.
//List<Tutorial> findByPublished(boolean isPublished, Sort sort);
//    tutorials = tutorialRepository.findByPublished(false, Sort.by("level").descending());
//    show(tutorials);

//    List<Order> orders = new ArrayList<Order>();
//orders.add(new Order(Sort.Direction.DESC, "level"));
//orders.add(new Order(Sort.Direction.ASC, "title"));
//
//    tutorials = tutorialRepository.findByPublished(true, Sort.by(orders));
//    show(tutorials);

//    Page<Tutorial> findAll(Pageable pageable);
//    int page = 0;
//    int size = 3;
//    Pageable pageable = PageRequest.of(page, size);
//    tutorials = tutorialRepository.findAll(pageable).getContent();
//    show(tutorials);

    //    Page<Tutorial> findByTitle(String title, Pageable pageable);
//    int page = 0;
//    int size = 3;
//    Pageable pageable = PageRequest.of(page, size);
//    tutorials = tutorialRepository.findByTitleContaining("ring", pageable).getContent();
//    show(tutorials);

//    Page<Tutorial> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

}

