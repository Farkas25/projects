using UnityEngine;
using System.Collections;

public class CloudMovement3D : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}

    // Update is called once per frame
    void Update ()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Ray toMouse = Camera.main.ScreenPointToRay(Input.mousePosition);
            RaycastHit rch;
            bool isHit = Physics.Raycast(toMouse, out rch, 500.0f);

            if (isHit)
            {
                print("Hit");
            }
            else
                print("No hit");
        }
        
    }
}
